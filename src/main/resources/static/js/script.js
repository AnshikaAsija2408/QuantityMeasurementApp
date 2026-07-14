// ===============================
// SAVE JWT TOKEN AFTER LOGIN
// ===============================

const params = new URLSearchParams(window.location.search);

const jwtToken = params.get("token");

if (jwtToken) {

    localStorage.setItem("token", jwtToken);

    window.history.replaceState(
        {},
        document.title,
        "/index.html"
    );

}
const units={

    LENGTH:[
        "FEET",
        "INCHES",
        "YARDS",
        "CENTIMETERS"
    ],

   WEIGHT:[
       "GRAM",
       "KILOGRAM",
       "POUND"
   ],

      VOLUME: [
            "LITRE",
            "MILLILITRE",
            "GALLON"
        ],

        TEMPERATURE: [
            "CELSIUS",
            "FAHRENHEIT"
        ]

};

let currentType="LENGTH";

let currentOperation="compare";

const typeButtons=document.querySelectorAll(".type-btn");

const operationButtons=document.querySelectorAll(".operation-btn");

const unit1=document.getElementById("unit1");

const unit2=document.getElementById("unit2");

const targetUnit=document.getElementById("targetUnit");

loadUnits(currentType);

updateOperationButtons();

typeButtons.forEach(button=>{

    button.onclick=()=>{

        typeButtons.forEach(btn=>btn.classList.remove("active"));

        button.classList.add("active");

        currentType = button.dataset.type;

        loadUnits(currentType);

        updateOperationButtons();

    };

});

operationButtons.forEach(button=>{

    button.onclick=()=>{

        operationButtons.forEach(btn=>btn.classList.remove("active"));

        button.classList.add("active");

        currentOperation=button.dataset.operation;

    };

});

function loadUnits(type){

    unit1.innerHTML="";

    unit2.innerHTML="";

    targetUnit.innerHTML="";

    units[type].forEach(unit=>{

        unit1.innerHTML+=`<option value="${unit}">${unit}</option>`;

        unit2.innerHTML+=`<option value="${unit}">${unit}</option>`;

        targetUnit.innerHTML+=`<option value="${unit}">${unit}</option>`;

    });

}

// =======================================
// FORM SUBMIT
// =======================================

const form = document.getElementById("quantityForm");
const resultBox = document.getElementById("resultBox");

form.addEventListener("submit", async function (event) {

    event.preventDefault();

    if (!validateForm()) {
        return;
    }

    const request = buildRequest();

    try {

        resultBox.innerHTML = "Calculating...";

        let response;

        switch (currentOperation) {

            case "compare":

                response = await callAPI("/compare", request);

              const compareMessage = response
                      ? "✅ Quantities are Equal"
                      : "❌ Quantities are Not Equal";

              resultBox.innerHTML = compareMessage;

saveHistory(

`COMPARE

${request.quantity1.value} ${request.quantity1.unit}

${request.quantity2.value} ${request.quantity2.unit}

Result : ${compareMessage}`

);                break;

            case "convert":

                response = await callAPI("/convert", request);

                const convertMessage =
                `${response.value} ${response.unit}`;

                resultBox.innerHTML =
                `<h2>${convertMessage}</h2>`;

saveHistory(

`CONVERT

${request.quantity1.value} ${request.quantity1.unit}

↓

${response.value} ${response.unit}`

);
                break;

            case "add":

                response = await callAPI("/add", request);

               const addMessage =
               `${response.value} ${response.unit}`;

               resultBox.innerHTML =
               `<h2>${addMessage}</h2>`;

saveHistory(

`ADD

${request.quantity1.value} ${request.quantity1.unit}

+

${request.quantity2.value} ${request.quantity2.unit}

=

${response.value} ${response.unit}`

);
                break;

            case "subtract":

                response = await callAPI("/subtract", request);

              const subtractMessage =
              `${response.value} ${response.unit}`;

              resultBox.innerHTML =
              `<h2>${subtractMessage}</h2>`;

saveHistory(

`SUBTRACT

${request.quantity1.value} ${request.quantity1.unit}

-

${request.quantity2.value} ${request.quantity2.unit}

=

${response.value} ${response.unit}`

);
                break;

            case "divide":

                response = await callAPI("/divide", request);

                const divideMessage = response;

                resultBox.innerHTML =
                `<h2>${divideMessage}</h2>`;

saveHistory(

`DIVIDE

${request.quantity1.value} ${request.quantity1.unit}

/

${request.quantity2.value} ${request.quantity2.unit}

=

${response}`

);
                break;
        }

    }

    catch (error) {

        console.error(error);

        resultBox.innerHTML = "❌ " + error.message;

    }

});

// =======================================
// RESET FORM
// =======================================

form.addEventListener("reset", () => {

    setTimeout(() => {

        resultBox.innerHTML = "Waiting for calculation...";

    }, 100);

});
// =======================================
// DYNAMIC FORM HANDLING
// =======================================

const targetUnitContainer =
    document.getElementById("targetUnitContainer");

const value2Input =
    document.getElementById("value2").parentElement;

const unit2Input =
    document.getElementById("unit2").parentElement;

updateUI();

operationButtons.forEach(button => {

    button.addEventListener("click", () => {

        currentOperation = button.dataset.operation;

        updateUI();

    });

});

function updateUI() {

    switch (currentOperation) {

        case "compare":

            value2Input.style.display = "flex";
            unit2Input.style.display = "flex";

            targetUnitContainer.style.display = "none";

            break;

        case "convert":

            value2Input.style.display = "none";
            unit2Input.style.display = "none";

            targetUnitContainer.style.display = "flex";

            break;

        case "add":

        case "subtract":

        case "divide":

            value2Input.style.display = "flex";
            unit2Input.style.display = "flex";

            targetUnitContainer.style.display = "none";

            break;

    }

}
function buildRequest() {

    const request = {

        quantity1: {

            value: Number(document.getElementById("value1").value),

            unit: document.getElementById("unit1").value,

            measurementType: currentType

        }

    };

    if(currentOperation !== "convert"){

        request.quantity2 = {

            value:Number(document.getElementById("value2").value),

            unit:document.getElementById("unit2").value,

            measurementType:currentType

        };

    }

    if(currentOperation==="convert"){

        request.targetUnit={

            value:0,

            unit:document.getElementById("targetUnit").value,

            measurementType:currentType

        };

    }

    return request;

}



function updateOperationButtons() {

    operationButtons.forEach(button => {

        const operation = button.dataset.operation;

        if (currentType === "TEMPERATURE") {

            if (
                operation === "add" ||
                operation === "subtract" ||
                operation === "divide"
            ) {

                button.style.display = "none";

            } else {

                button.style.display = "inline-block";

            }

        } else {

            button.style.display = "inline-block";

        }

    });

    // Agar Temperature select hai aur current operation invalid hai
    if (
        currentType === "TEMPERATURE" &&
        (
            currentOperation === "add" ||
            currentOperation === "subtract" ||
            currentOperation === "divide"
        )
    ) {

        currentOperation = "compare";

        operationButtons.forEach(btn => {

            btn.classList.remove("active");

            if (btn.dataset.operation === "compare") {

                btn.classList.add("active");

            }

        });

        updateUI();

    }

}
function saveHistory(text){

    let history =
        JSON.parse(localStorage.getItem("history")) || [];

    history.push(text);

    if(history.length > 10){

        history.shift();

    }

    localStorage.setItem(
        "history",
        JSON.stringify(history)
    );

}

// =========================
// LOGOUT
// =========================

const logoutBtn = document.getElementById("logoutBtn");

logoutBtn.addEventListener("click", () => {

    localStorage.removeItem("token");

    localStorage.removeItem("history");

    window.location.href = "/logout";

});