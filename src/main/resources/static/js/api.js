const BASE_URL="http://localhost:8080/api/quantity";

async function callAPI(endpoint,data){

    const token=localStorage.getItem("token");

    const response=await fetch(BASE_URL+endpoint,{

        method:"POST",

        headers:{

            "Content-Type":"application/json",

            "Authorization":"Bearer "+token

        },

        body:JSON.stringify(data)

    });

    if(!response.ok){

        throw new Error(await response.text());

    }

    return await response.json();

}