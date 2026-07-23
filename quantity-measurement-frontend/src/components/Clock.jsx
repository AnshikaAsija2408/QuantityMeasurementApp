import React, { useState, useEffect } from "react";
import "../styles/clock.css";

// Replaces iframe/clock.html
export default function Clock() {
  const [now, setNow] = useState(new Date());

  useEffect(() => {
    const interval = setInterval(() => {
      setNow(new Date());
    }, 1000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="clock-widget">
      <div className="clock">
        <div id="time">{now.toLocaleTimeString()}</div>
        <div id="date">{now.toDateString()}</div>
      </div>
    </div>
  );
}