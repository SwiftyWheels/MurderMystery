// This places the JS in strict mode which will throw errors when the code
// isn't written properly. Helps with debugging.
// This can also be placed inside a function to make the function strict mode
"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {

    // Game Area
    const gameArea = document.querySelector(".game-area");
    const imageBox = gameArea.querySelector(".image-box");
    const currentImg = imageBox.querySelector("img");
    const dialogueBox = gameArea.querySelector(".dialogue-box");
    const button = dialogueBox.querySelector("#dialogueButton");
    const dialogueParagraph = dialogueBox.querySelector("#dialogueParagraph");
    const dialogueOptions = dialogueBox.querySelector(".dialogue-options");

    // Notes Area
    const notesBox = document.querySelector(".game .notes-area .notes-container");
    const notesTextArea = notesBox.querySelector("#notes");

    // Inventory Area
    const inventoryArea = document.querySelector(".inventory-area");
    const inventoryContainer = inventoryArea.querySelector(".inventory-container");
    const inventoryList = inventoryContainer.querySelector(".inventory-list");


    button.addEventListener("click", () => updateScene());

    // notesTextArea.addEventListener("input", () => updateNotes())

    async function updateScene() {
        await fetchDialogue(dialogueParagraph.dataset.speaker);
        await checkForOptionEvent(dialogueParagraph.dataset.id);
        await updateNotes(notesTextArea.value);
        await checkForEvent(dialogueParagraph.dataset.speaker, dialogueParagraph.dataset.id)
            .then(() => checkForFlagEvent(dialogueParagraph.dataset.id));
        await updateInventory();
    }

    async function fetchDialogue(name) {
        name = name.toLowerCase();
        const endPoint = "/api/dialogue/getPersonDialogue/" + name;
        try {
            const response = await fetch(endPoint);
            if (response.ok) {
                if (dialogueParagraph) {
                    const json = await response.json();
                    const text = json.text;
                    const id = json.id;
                    dialogueParagraph.innerText = text;
                    dialogueParagraph.dataset.id = id;
                    currentImg.src = "/imgs/characters/" + name + "/" + id + ".jpg";
                }
            }
        } catch (e) {
            let p = document.createElement("p");
            p.innerText = await e.text();
            p.appendChild(p);
        }
    }

    async function updateNotes(text) {
        const endPoint = "/api/notes/setNoteText/" + text;
        try {
            const response = await fetch(endPoint);
            if (response.ok) {
            }
        } catch (e) {
            console.log("Could not update notes!");
        }
    }

    async function updateInventory() {
        const endPoint = "/api/inventory/getInventoryItems";
        try {
            const response = await fetch(endPoint);
            if (response.ok) {
                const json = await response.json();
                const inventory = json.itemList;

                removeInventoryItems();

                for (const inventoryElement of inventory) {
                    createInventoryItemElement(inventoryElement.itemName);
                }
            }
        } catch (e) {
            console.log(e);
        }
    }

    function createInventoryItemElement(text) {
        if (inventoryList) {
            if (!hasItem(text)) {
                const listItem = document.createElement("li");
                listItem.innerText = text;
                inventoryList.appendChild(listItem);
            }
        }
    }

    async function checkForEvent(speaker, id) {
        const endPoint = "/api/events/checkForEvent/" + speaker + "/" + id;
        const event = await fetch(endPoint).then(response => response.text()).then(results => {
            if (results) {
                const json = JSON.parse(results);
                const eventURL = json.url;
                return fetch(eventURL);
            }
        }).catch();
    }

    function checkForOptionEvent(id) {
        let dialogueID = parseInt(id);
        showButton(button);
        removeOptionButtons();
        switch (dialogueID) {
            case 13 : {
                createEventButton("narrator", 13, "Nothing", "");
                createEventButton("narrator", 15, "Knife", "/api/inventory/addItem/Knife");
                createEventButton("narrator", 16, "Lamp", "/api/inventory/addItem/Lamp");
                hideButton(button);
                break;
            }
            case 18 : {
                createEventButton("narrator", 19, "Attack", "");
                createEventButton("narrator", 23, "Take a chance", "");
                hideButton(button);
                break;
            }
            case 38 : {
                createEventButton("narrator", 39, "Keep lights off", "");
                createEventButton("narrator", 41, "Turn lights on", "");
                hideButton(button);
                break;
            }
            case 48 : {
                createEventButton("narrator", 49, "Trust mom", "");
                createEventButton("narrator", 56, "Do nothing", "")
                hideButton(button);
                break;
            }
            case 60 : {
                createEventButton("narrator", 61, "Attack Mom", "");
                createEventButton("narrator", 63, "Attack Aunt", "");
                hideButton(button);
                break;
            }
            case 68 : {
                if (hasItem("Knife")) {
                    createEventButton("narrator", 71, "Attack Mom", "");
                }else if (hasItem("Lamp")) {
                    createEventButton("narrator", 76, "Attack Mom", "");
                }else{
                    createEventButton("narrator", 69, "Attack Mom", "");
                }

                createEventButton("narrator", 73, "Attack Aunt", "");
                hideButton(button);
                break;
            }
            case 79 : {
                hideButton(button);
                createRestartButton();
                break;
            }
            case 80 : {
                hideButton(button);
                createRestartButton();
                break;
            }
        }
    }

    async function checkForFlagEvent(id) {
        let dialogueID = parseInt(id);
        switch (dialogueID) {
            case 19 : {
                if (hasItem("Knife")) {
                    callAPI("/api/dialogue/skipToDialogue/narrator/20")
                        .then(() => callAPI("/api/storyFlag/setStoryFlagValue/sisterAttackKnife/true"))
                        .then(() => callAPI("/api/inventory/removeItem/Knife"))
                        .catch();
                } else if (hasItem("Lamp")) {
                    callAPI("/api/dialogue/skipToDialogue/narrator/21")
                        .then(() => callAPI("/api/storyFlag/setStoryFlagValue/sisterAttackLamp/true"))
                        .then(() => callAPI("/api/inventory/removeItem/Lamp"))
                        .catch();
                } else {
                    skipToScene("narrator", 22).catch();
                }
                break;
            }
            case 22 : {
                callAPI("/api/storyFlag/setStoryFlagValue/sisterAttackNone/true").catch();
                break;
            }
            case 25 : {
                await hasFlag("sisterAttackNone").then(value => {
                    let isTrue = parseBoolean(value);
                    if (isTrue) {
                        callAPI("/api/dialogue/skipToDialogue/narrator/26").catch();
                    } else {
                        callAPI("/api/dialogue/skipToDialogue/narrator/27").catch();
                    }
                });
                break;
            }
            case 29 : {
                await hasFlag("sisterAttackKnife").then(attackKnife => {
                    let isTrue = parseBoolean(attackKnife);
                    if (isTrue) {
                        callAPI("/api/dialogue/skipToDialogue/narrator/30").catch();
                    }
                    return isTrue;
                }).then(attackKnife => {
                    console.log(attackKnife);
                    if (!attackKnife) {
                        hasFlag("sisterAttackLamp").then(attackLamp => {
                            let isTrue = parseBoolean(attackLamp);
                            if (isTrue) {
                                callAPI("/api/dialogue/skipToDialogue/narrator/31").catch();
                            } else {
                                callAPI("/api/dialogue/skipToDialogue/narrator/33").catch();
                            }
                        });
                    }
                });
                break;
            }
            case 49 : {
                await hasFlag("sisterAttackNone").then(attackNone => {
                    let isTrue = parseBoolean(attackNone);
                    if (isTrue) {
                        callAPI("/api/dialogue/skipToDialogue/narrator/50").catch();
                    }
                    return isTrue;
                }).then(attackNone => {
                    if (!attackNone) {
                        hasFlag("sisterAttackLamp").then(attackLamp => {
                            let isTrue = parseBoolean(attackLamp);
                            if (isTrue) {
                                callAPI("/api/dialogue/skipToDialogue/narrator/53").catch();
                            } else {
                                callAPI("/api/dialogue/skipToDialogue/narrator/50").catch();
                            }
                        });
                    }
                });
                break;
            }
            case 57 : {
                await hasFlag("sisterAttackNone").then(attackNone => {
                    let isTrue = parseBoolean(attackNone);
                    if (isTrue) {
                        callAPI("/api/dialogue/skipToDialogue/narrator/66").catch();
                    }
                    return isTrue;
                }).then(attackNone => {
                    if (!attackNone) {
                        hasFlag("sisterAttackLamp").then(attackLamp => {
                            let isTrue = parseBoolean(attackLamp);
                            if (isTrue) {
                                callAPI("/api/dialogue/skipToDialogue/narrator/58").catch();
                            } else {
                                callAPI("/api/dialogue/skipToDialogue/narrator/58").catch();
                            }
                        });
                    }
                });
                break;
            }
        }
    }

    /* Creates a button with an event handler. The button when clicked will send an api call to the URL.
     * The button will also skip to the dialogue id provided in the arguments.*/
    function createEventButton(name, id, optionContent, url) {
        console.log("Creating button!");
        const button = document.createElement("button");
        button.id = id;
        button.innerText = optionContent;

        button.addEventListener("click", async () => {
            if (url) {
                const urlEvent = await fetch(url).then(async () => {
                    await skipToScene(name, id);
                }).catch();
            } else {
                await skipToScene(name, id);
            }
        });
        dialogueOptions.appendChild(button);
    }

    async function skipToScene(name, id) {
        const endPoint = "/api/dialogue/skipToDialogue/" + name + "/" + id;
        return await fetch(endPoint).then(response => response.text()).then(() => updateScene()).catch();
    }

    async function callAPI(url) {
        return await fetch(url).then(response => response.text());
    }

    async function hasFlag(flagName) {
        const result = await callAPI("/api/storyFlag/getStoryFlagValue/" + flagName);
        console.log(result);
        if (result) {
            return result;
        }
        return false;
    }

    function hasItem(itemName) {
        if (inventoryList) {
            for (const inventoryListElement of inventoryList.children) {
                if (inventoryListElement.innerText === itemName) {
                    console.log("Has Item: " + itemName);
                    return true;
                }
            }
        }
        return false;
    }

    function hideButton(button) {
        button.style.display = "none";
    }

    function showButton(button) {
        button.style.display = "block";
    }

    function removeOptionButtons() {
        let firstChild = dialogueOptions.firstElementChild;

        while (firstChild) {
            firstChild.remove();
            firstChild = dialogueOptions.firstElementChild;
        }
    }

    function removeInventoryItems() {
        let firstChild = inventoryList.firstElementChild;
        while (firstChild) {
            firstChild.remove();
            firstChild = inventoryList.firstElementChild;
        }
    }

    function parseBoolean(value) {
        return value === "true";
    }

    function createRestartButton(){
        if (dialogueOptions) {
            console.log("Creating restart button");
            let button = document.createElement("button");
            button.innerText = "Restart";
            button.addEventListener("click", () => location.reload());
            dialogueOptions.appendChild(button);
        }
    }
}