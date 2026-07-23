import React from "react";
import { Navigate } from "react-router-dom";
import { isAuthenticated } from "../services/authService.js";

// Guards the main app route — if there's no token,
// bounce the user back to the Dashboard/Sign-in page.
export default function ProtectedRoute({ children }) {
  if (!isAuthenticated()) {
    return <Navigate to="/" replace />;
  }

  return children;
}