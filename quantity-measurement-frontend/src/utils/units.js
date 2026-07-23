// Same unit map used by the original script.js
export const UNITS = {
  LENGTH: ["FEET", "INCHES", "YARDS", "CENTIMETERS"],
  WEIGHT: ["GRAM", "KILOGRAM", "POUND"],
  VOLUME: ["LITRE", "MILLILITRE", "GALLON"],
  TEMPERATURE: ["CELSIUS", "FAHRENHEIT"],
};

// Operations that Temperature does not support (matches updateOperationButtons)
export const TEMPERATURE_RESTRICTED_OPERATIONS = ["add", "subtract", "divide"];