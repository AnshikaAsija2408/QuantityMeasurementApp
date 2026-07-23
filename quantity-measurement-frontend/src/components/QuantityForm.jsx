import React, { useState, useEffect } from "react";
import { UNITS } from "../utils/units.js";
import { validateForm } from "../utils/validation.js";
import { saveHistory } from "../services/historyService.js";
import {
  compareQuantities,
  convertQuantity,
  addQuantities,
  subtractQuantities,
  divideQuantities,
} from "../api/quantityApi.js";

// Builds the request body exactly like the old buildRequest()
function buildRequest({
  value1,
  unit1,
  value2,
  unit2,
  targetUnit,
  currentType,
  currentOperation,
}) {
  const request = {
    quantity1: {
      value: Number(value1),
      unit: unit1,
      measurementType: currentType,
    },
  };

  if (currentOperation !== "convert") {
    request.quantity2 = {
      value: Number(value2),
      unit: unit2,
      measurementType: currentType,
    };
  }

  if (currentOperation === "convert") {
    request.targetUnit = {
      value: 0,
      unit: targetUnit,
      measurementType: currentType,
    };
  }

  return request;
}

export default function QuantityForm({
  currentType,
  currentOperation,
  onResult,
  onHistorySaved,
}) {
  const [value1, setValue1] = useState("");
  const [value2, setValue2] = useState("");
  const [unit1, setUnit1] = useState(UNITS.LENGTH[0]);
  const [unit2, setUnit2] = useState(UNITS.LENGTH[0]);
  const [targetUnit, setTargetUnit] = useState(UNITS.LENGTH[0]);

  // Equivalent of loadUnits(currentType) — rebuild unit dropdown
  // options whenever the measurement type changes.
  useEffect(() => {
    const list = UNITS[currentType];
    setUnit1(list[0]);
    setUnit2(list[0]);
    setTargetUnit(list[0]);
  }, [currentType]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!validateForm(value1, value2, currentOperation)) {
      return;
    }

    const request = buildRequest({
      value1,
      unit1,
      value2,
      unit2,
      targetUnit,
      currentType,
      currentOperation,
    });

    try {
      onResult({ status: "loading" });

      let response;

      switch (currentOperation) {
        case "compare": {
          response = await compareQuantities(request);

          const compareMessage = response
            ? "✅ Quantities are Equal"
            : "❌ Quantities are Not Equal";

          onResult({ status: "success", type: "text", content: compareMessage });

          saveHistory(
            `COMPARE\n\n${request.quantity1.value} ${request.quantity1.unit}\n\n${request.quantity2.value} ${request.quantity2.unit}\n\nResult : ${compareMessage}`
          );
          onHistorySaved();
          break;
        }

        case "convert": {
          response = await convertQuantity(request);

          const convertMessage = `${response.value} ${response.unit}`;

          onResult({ status: "success", type: "heading", content: convertMessage });

          saveHistory(
            `CONVERT\n\n${request.quantity1.value} ${request.quantity1.unit}\n\n↓\n\n${response.value} ${response.unit}`
          );
          onHistorySaved();
          break;
        }

        case "add": {
          response = await addQuantities(request);

          const addMessage = `${response.value} ${response.unit}`;

          onResult({ status: "success", type: "heading", content: addMessage });

          saveHistory(
            `ADD\n\n${request.quantity1.value} ${request.quantity1.unit}\n\n+\n\n${request.quantity2.value} ${request.quantity2.unit}\n\n=\n\n${response.value} ${response.unit}`
          );
          onHistorySaved();
          break;
        }

        case "subtract": {
          response = await subtractQuantities(request);

          const subtractMessage = `${response.value} ${response.unit}`;

          onResult({ status: "success", type: "heading", content: subtractMessage });

          saveHistory(
            `SUBTRACT\n\n${request.quantity1.value} ${request.quantity1.unit}\n\n-\n\n${request.quantity2.value} ${request.quantity2.unit}\n\n=\n\n${response.value} ${response.unit}`
          );
          onHistorySaved();
          break;
        }

        case "divide": {
          response = await divideQuantities(request);

          const divideMessage = response;

          onResult({ status: "success", type: "heading", content: divideMessage });

          saveHistory(
            `DIVIDE\n\n${request.quantity1.value} ${request.quantity1.unit}\n\n/\n\n${request.quantity2.value} ${request.quantity2.unit}\n\n=\n\n${response}`
          );
          onHistorySaved();
          break;
        }

        default:
          break;
      }
    } catch (error) {
      console.error(error);
      onResult({ status: "error", type: "text", content: "❌ " + error.message });
    }
  };

  // Equivalent of the old form "reset" event listener:
  // fields reset immediately, resultBox message resets
  // after 100ms (matching the original setTimeout timing).
  const handleFormReset = () => {
    const list = UNITS[currentType];

    setValue1("");
    setValue2("");
    setUnit1(list[0]);
    setUnit2(list[0]);
    setTargetUnit(list[0]);

    setTimeout(() => {
      onResult({ status: "idle" });
    }, 100);
  };

  const isConvert = currentOperation === "convert";

  return (
    <section className="card">
      <h2>Enter Quantity Details</h2>

      <form id="quantityForm" onSubmit={handleSubmit} onReset={handleFormReset}>
        <div className="form-grid">
          <div className="input-group">
            <label>Value 1</label>
            <input
              type="number"
              id="value1"
              step="any"
              placeholder="Enter Value"
              value={value1}
              onChange={(e) => setValue1(e.target.value)}
            />
          </div>

          <div className="input-group">
            <label>Unit</label>
            <select
              id="unit1"
              value={unit1}
              onChange={(e) => setUnit1(e.target.value)}
            >
              {UNITS[currentType].map((u) => (
                <option key={u} value={u}>
                  {u}
                </option>
              ))}
            </select>
          </div>

          <div
            className="input-group"
            style={{ display: isConvert ? "none" : "flex" }}
          >
            <label>Value 2</label>
            <input
              type="number"
              id="value2"
              step="any"
              placeholder="Enter Value"
              value={value2}
              onChange={(e) => setValue2(e.target.value)}
            />
          </div>

          <div
            className="input-group"
            style={{ display: isConvert ? "none" : "flex" }}
          >
            <label>Unit</label>
            <select
              id="unit2"
              value={unit2}
              onChange={(e) => setUnit2(e.target.value)}
            >
              {UNITS[currentType].map((u) => (
                <option key={u} value={u}>
                  {u}
                </option>
              ))}
            </select>
          </div>
        </div>

        {/* Target Unit */}
        <div
          className="input-group"
          id="targetUnitContainer"
          style={{ display: isConvert ? "flex" : "none" }}
        >
          <label>Target Unit</label>
          <select
            id="targetUnit"
            value={targetUnit}
            onChange={(e) => setTargetUnit(e.target.value)}
          >
            {UNITS[currentType].map((u) => (
              <option key={u} value={u}>
                {u}
              </option>
            ))}
          </select>
        </div>

        <div className="button-group">
          <button type="submit" id="calculateBtn">
            Calculate
          </button>
          <button type="reset">Reset</button>
        </div>
      </form>
    </section>
  );
}