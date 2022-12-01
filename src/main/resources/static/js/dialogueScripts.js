// This places the JS in strict mode which will throw errors when the code
// isn't written properly. Helps with debugging.
// This can also be placed inside a function to make the function strict mode
"use strict";

document.addEventListener("DOMContentLoaded", init);

function init(){
    const button = document.querySelector("#dialogueButton");
    const dialogue = document.querySelector("#dialogue");
    button.addEventListener("click", fetchDialogue);

    async function fetchDialogue (){
        const endPoint = "/getPersonDialogue/Test";

        try{
            const response = await fetch(endPoint);
            if (response.ok) {
                let p = document.createElement("p");
                p.innerText = await response.text();
                dialogue.appendChild(p);
            }
        }catch (e) {
            let p = document.createElement("p");
            p.innerText = await e.text();
            dialogue.appendChild(p);
        }
    }

}