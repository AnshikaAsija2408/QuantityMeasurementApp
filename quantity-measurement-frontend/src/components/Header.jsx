import React from "react";
import Clock from "./Clock.jsx";
import UserProfile from "./UserProfile.jsx";
import { logout } from "../services/authService.js";

export default function Header() {
  const handleLogout = () => {
    logout();
  };

  return (
    <header>
      <div className="logo">
        <i className="fa-solid fa-ruler-combined"></i>
        <h1>Quantity Measurement</h1>
      </div>

      <div className="header-right">
        <UserProfile />

        <div className="clock-frame">
          <Clock />
        </div>

        <button id="logoutBtn" onClick={handleLogout}>
          <i className="fa-solid fa-right-from-bracket"></i>
          Logout
        </button>
      </div>
    </header>
  );
}