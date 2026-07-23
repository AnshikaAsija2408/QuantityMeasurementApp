// Direct port of the original js/validation.js
// (currentOperation / value1 / value2 are now passed in as
// arguments instead of being read from the DOM / globals)
export function validateForm(value1, value2, currentOperation) {
  if (value1 === "") {
    alert("Enter First Value");
    return false;
  }

  if (currentOperation !== "convert") {
    if (value2 === "") {
      alert("Enter Second Value");
      return false;
    }

    if (currentOperation === "divide" && Number(value2) === 0) {
      alert("Cannot divide by zero.");
      return false;
    }
  }

  return true;
}