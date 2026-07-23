import React, { useState } from "react";
import Header from "../components/Header.jsx";
import MeasurementTypeSelector from "../components/MeasurementTypeSelector.jsx";
import OperationSelector from "../components/OperationSelector.jsx";
import QuantityForm from "../components/QuantityForm.jsx";
import ResultBox from "../components/ResultBox.jsx";
import HistoryPanel from "../components/HistoryPanel.jsx";
import UserManual from "../components/UserManual.jsx";
import Footer from "../components/Footer.jsx";
import { TEMPERATURE_RESTRICTED_OPERATIONS } from "../utils/units.js";

export default function HomePage() {
  const [currentType, setCurrentType] = useState("LENGTH");
  const [currentOperation, setCurrentOperation] = useState("compare");
  const [result, setResult] = useState({ status: "idle" });
  const [historyRefreshTrigger, setHistoryRefreshTrigger] = useState(0);

  // Equivalent of the type-btn click handler + updateOperationButtons()
  // auto-switch-to-compare logic for Temperature.
  const handleTypeChange = (type) => {
    setCurrentType(type);

    if (
      type === "TEMPERATURE" &&
      TEMPERATURE_RESTRICTED_OPERATIONS.includes(currentOperation)
    ) {
      setCurrentOperation("compare");
    }
  };

  const handleOperationChange = (operation) => {
    setCurrentOperation(operation);
  };

  const handleHistorySaved = () => {
    setHistoryRefreshTrigger((prev) => prev + 1);
  };

  return (
    <>
      <Header />

      <main className="container">
        {/* ============================ */}
        {/* Measurement Type */}
        {/* ============================ */}
        <MeasurementTypeSelector
          currentType={currentType}
          onTypeChange={handleTypeChange}
        />

        {/* ============================ */}
        {/* Operation */}
        {/* ============================ */}
        <OperationSelector
          currentType={currentType}
          currentOperation={currentOperation}
          onOperationChange={handleOperationChange}
        />

        {/* ============================ */}
        {/* Form */}
        {/* ============================ */}
        <QuantityForm
          currentType={currentType}
          currentOperation={currentOperation}
          onResult={setResult}
          onHistorySaved={handleHistorySaved}
        />

        {/* ============================ */}
        {/* Result */}
        {/* ============================ */}
        <ResultBox result={result} />

        {/* ============================ */}
        {/* Calculation History */}
        {/* ============================ */}
        <HistoryPanel refreshTrigger={historyRefreshTrigger} />

        <UserManual />
      </main>

      <Footer />
    </>
  );
}