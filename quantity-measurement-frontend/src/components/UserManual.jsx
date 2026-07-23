import React from "react";
import "../styles/userManual.css";

// Replaces iframe/user-manual.html
export default function UserManual() {
  return (
    <section className="card">
      <h2>User Manual</h2>

      <div className="manual-frame">
        <div className="manual-widget">
          <h2 className="manual-heading">📖 User Manual</h2>

          <h3>Step 1</h3>
          <p>Select Measurement Type.</p>
          <ul>
            <li>Length</li>
            <li>Weight</li>
            <li>Volume</li>
            <li>Temperature</li>
          </ul>

          <h3>Step 2</h3>
          <p>Select the required operation.</p>
          <ul>
            <li>Compare</li>
            <li>Convert</li>
            <li>Add</li>
            <li>Subtract</li>
            <li>Divide</li>
          </ul>

          <h3>Step 3</h3>
          <p>Enter the required values and units.</p>

          <h3>Step 4</h3>
          <p>
            Click <b>Calculate</b>.
          </p>

          <h3>Supported Units</h3>
          <ul>
            <li>Length → Feet, Inches, Yards, Centimeters</li>
            <li>Weight → Gram, Kilogram, Pound</li>
            <li>Volume → Litre, Millilitre, Gallon</li>
            <li>Temperature → Celsius, Fahrenheit</li>
          </ul>

          <div className="note">
            <b>Note:</b> Temperature supports only <b>Compare</b> and{" "}
            <b>Convert</b> operations. Arithmetic operations are not
            supported.
          </div>
        </div>
      </div>
    </section>
  );
}