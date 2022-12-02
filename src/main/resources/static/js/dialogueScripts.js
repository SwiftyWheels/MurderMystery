// This places the JS in strict mode which will throw errors when the code
// isn't written properly. Helps with debugging.
// This can also be placed inside a function to make the function strict mode
"use strict";

document.addEventListener("DOMContentLoaded", init);

function init() {
    const gameArea = document.querySelector(".game-area");
    const imageBox = gameArea.querySelector(".image-box");
    const currentImg = imageBox.querySelector("img");
    const dialogueBox = gameArea.querySelector(".dialogue-box");
    const button = dialogueBox.querySelector("#dialogueButton");
    const dialogueParagraph = dialogueBox.querySelector("#dialogueParagraph");

    const notesBox = document.querySelector(".game .notes-area .notes-container");
    const notesTextArea = notesBox.querySelector("#notes");

    button.addEventListener("click", () => updateScene(dialogueParagraph.dataset.speaker, notesTextArea.value));

    // notesTextArea.addEventListener("input", () => updateNotes())

    async function updateScene(name, text) {
        await fetchDialogue(name);
        await updateNotes(text)
    }


    async function fetchDialogue(name) {
        const endPoint = "/api/dialogue/getPersonDialogue/" + name;
        try {
            const response = await fetch(endPoint);
            if (response.ok) {
                if (dialogueParagraph) {
                    const json = await response.json();
                    const text = json.text;
                    const id = json.id;
                    const imgURI = "/imgs/characters/" + name + "/" + id + ".jpg";
                    dialogueParagraph.innerText = text;
                    dialogueParagraph.dataset.id = id;
                    currentImg.src = imgURI;
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
                console.log("Successfully updated notes!")
            }
        } catch (e) {
            console.log("Could not update notes!");
        }
    }
}