// Direct port of the original js/api.js
// Backend base URL updated to point to the quantity-service microservice (port 8081).
const BASE_URL = "http://localhost:8081/api/quantity";

async function callAPI(endpoint, data) {
  const token = localStorage.getItem("token");

  const response = await fetch(BASE_URL + endpoint, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error(await response.text());
  }

  return await response.json();
}

export async function compareQuantities(request) {
  return callAPI("/compare", request);
}

export async function convertQuantity(request) {
  return callAPI("/convert", request);
}

export async function addQuantities(request) {
  return callAPI("/add", request);
}

export async function subtractQuantities(request) {
  return callAPI("/subtract", request);
}

export async function divideQuantities(request) {
  return callAPI("/divide", request);
}

export default callAPI;