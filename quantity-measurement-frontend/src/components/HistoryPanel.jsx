import React, { useState, useEffect, useCallback } from "react";
import { getHistory } from "../services/historyService.js";
import "../styles/history.css";

// Replaces iframe/history.html.
// Keeps the same polling / storage-event behavior as the
// original (setInterval every 1s + "storage" listener),
// plus an immediate refresh right after a calculation is saved.
export default function HistoryPanel({ refreshTrigger }) {
  const [history, setHistory] = useState([]);

  const loadHistory = useCallback(() => {
    setHistory(getHistory());
  }, []);

  useEffect(() => {
    loadHistory();

    window.addEventListener("storage", loadHistory);
    const interval = setInterval(loadHistory, 1000);

    return () => {
      window.removeEventListener("storage", loadHistory);
      clearInterval(interval);
    };
  }, [loadHistory]);

  useEffect(() => {
    loadHistory();
  }, [refreshTrigger, loadHistory]);

  return (
    <section className="card">
      <h2>Calculation History</h2>

      <div className="history-frame">
        <div className="history-widget">
          {history.length === 0 ? (
            <p className="empty">No Calculations Yet</p>
          ) : (
            history.map((item, index) => (
              <div className="history-card" key={index}>
                <pre>{item}</pre>
              </div>
            ))
          )}
        </div>
      </div>
    </section>
  );
}