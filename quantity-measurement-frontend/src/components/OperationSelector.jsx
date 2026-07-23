import React from "react";
import { TEMPERATURE_RESTRICTED_OPERATIONS } from "../utils/units.js";

const OPERATIONS = [
  { operation: "compare", label: "Compare" },
  { operation: "convert", label: "Convert" },
  { operation: "add", label: "Add" },
  { operation: "subtract", label: "Subtract" },
  { operation: "divide", label: "Divide" },
];

// Replicates updateOperationButtons() — hides Add/Subtract/Divide
// when Temperature is selected.
export default function OperationSelector({
  currentType,
  currentOperation,
  onOperationChange,
}) {
  return (
    <section className="card">
      <h2>Select Operation</h2>

      <div className="operations">
        {OPERATIONS.map(({ operation, label }) => {
          const isHiddenForTemperature =
            currentType === "TEMPERATURE" &&
            TEMPERATURE_RESTRICTED_OPERATIONS.includes(operation);

          return (
            <button
              key={operation}
              className={
                "operation-btn" +
                (currentOperation === operation ? " active" : "")
              }
              data-operation={operation}
              style={{
                display: isHiddenForTemperature ? "none" : "inline-block",
              }}
              onClick={() => onOperationChange(operation)}
            >
              {label}
            </button>
          );
        })}
      </div>
    </section>
  );
}