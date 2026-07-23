import React from "react";

const TYPES = [
  { type: "LENGTH", icon: "fa-solid fa-ruler", label: "Length" },
  { type: "WEIGHT", icon: "fa-solid fa-weight-scale", label: "Weight" },
  {
    type: "TEMPERATURE",
    icon: "fa-solid fa-temperature-half",
    label: "Temperature",
  },
  { type: "VOLUME", icon: "fa-solid fa-glass-water", label: "Volume" },
];

export default function MeasurementTypeSelector({
  currentType,
  onTypeChange,
}) {
  return (
    <section className="card">
      <h2>Select Measurement Type</h2>

      <div className="measurement-types">
        {TYPES.map(({ type, icon, label }) => (
          <button
            key={type}
            className={
              "type-btn" + (currentType === type ? " active" : "")
            }
            data-type={type}
            onClick={() => onTypeChange(type)}
          >
            <i className={icon}></i>
            <span>{label}</span>
          </button>
        ))}
      </div>
    </section>
  );
}