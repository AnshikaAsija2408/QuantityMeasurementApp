function validateForm(){

    const value1=document.getElementById("value1").value;

    if(value1===""){

        alert("Enter First Value");

        return false;

    }

    if(currentOperation!=="convert"){

        const value2=document.getElementById("value2").value;

        if(value2===""){

            alert("Enter Second Value");

            return false;

        }

        if(currentOperation==="divide" && Number(value2)===0){

            alert("Cannot divide by zero.");

            return false;

        }

    }

    return true;

}