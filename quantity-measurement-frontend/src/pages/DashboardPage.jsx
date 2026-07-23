import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { captureTokenFromUrl, isAuthenticated } from "../services/authService.js";
import "../styles/dashboard.css";

const BACKEND_URL = "http://localhost:8080";

// This is the very first page the user sees at "/".
// It captures the JWT token if it just arrived from the OAuth
// redirect, and if the user is already signed in, sends them
// straight to the main app.
export default function DashboardPage() {
  const navigate = useNavigate();

  useEffect(() => {
    captureTokenFromUrl();

    if (isAuthenticated()) {
      navigate("/app", { replace: true });
    }
  }, [navigate]);

  const handleGoogleSignIn = () => {
    window.location.href = BACKEND_URL + "/oauth2/authorization/google";
  };

  return (
    <div className="dashboard-page">
      {/* Left brand panel */}
      <div className="dashboard-panel">
        <div className="dashboard-panel-content">
          <div className="dashboard-logo">
            <i className="fa-solid fa-ruler-combined"></i>
          </div>

          <h1>Quantity Measurement</h1>
          <p>
            Compare, convert, add, subtract and divide Length, Weight,
            Volume and Temperature quantities — all in one place.
          </p>

          <ul className="dashboard-feature-list">
            <li>
              <i className="fa-solid fa-check"></i> Real-time calculations
            </li>
            <li>
              <i className="fa-solid fa-check"></i> Calculation history
            </li>
            <li>
              <i className="fa-solid fa-check"></i> Secure Google sign-in
            </li>
          </ul>
        </div>
      </div>

      {/* Right sign-in card */}
      <div className="dashboard-signin">
        <div className="signin-card">
          <div className="signin-logo">
            <i className="fa-solid fa-ruler-combined"></i>
          </div>

          <h2>Welcome Back</h2>
          <p className="signin-subtitle">
            Sign in to continue to your dashboard
          </p>

          <button className="google-signin-btn" onClick={handleGoogleSignIn}>
            <i className="fa-brands fa-google"></i>
            Sign in with Google
          </button>

          <p className="signin-footer-text">
            Secure login powered by Google OAuth &amp; JWT
          </p>
        </div>
      </div>
    </div>
  );
}