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

    // Notes Area
    const notesBox = document.querySelector(".game .notes-area .notes-container");
    const notesTextArea = notesBox.querySelector("#notes");

    // Inventory Area
    const inventoryArea = document.querySelector(".inventory-area");
    const inventoryContainer = inventoryArea.querySelector(".inventory-container");
    const inventoryList = inventoryContainer.querySelector(".inventory-list");


    button.addEventListener("click", () => updateScene(dialogueParagraph.dataset.speaker, notesTextArea.value));

    // notesTextArea.addEventListener("input", () => updateNotes())

    async function updateScene(name, text) {
        await fetchDialogue(name);
        await updateNotes(text);
        await checkForEvent(dialogueParagraph.dataset.speaker, dialogueParagraph.dataset.id);
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
                    currentImg.src = "/imgs/characters/"+name+"/"+id+".jpg";
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

    async function updateInventory(){
        const endPoint = "/api/inventory/getInventoryItems";
        try{
            const response = await fetch(endPoint);
            if (response.ok) {
                const json = await response.json();
                const inventory = json.itemList;

                for (const inventoryElement of inventory) {
                    createInventoryItemElement(inventoryElement.itemName);
                }
            }
        }catch (e) {
            console.log(e);
        }
    }

    function createInventoryItemElement(text){
        if (inventoryList){
            let hasItem = false;

            for (const inventoryListElement of inventoryList.children) {
                if (inventoryListElement.innerText === text) {
                    hasItem = true;
                }
            }

            if (hasItem === false) {
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
}