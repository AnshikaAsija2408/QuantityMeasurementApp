import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import DashboardPage from "./pages/DashboardPage.jsx";
import HomePage from "./pages/HomePage.jsx";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import "./styles/style.css";
import "./styles/responsive.css";

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Dashboard / Sign-in — always the first page */}
        <Route path="/" element={<DashboardPage />} />

        {/* Main Quantity Measurement app — only after sign-in */}
        <Route
          path="/app"
          element={
            <ProtectedRoute>
              <HomePage />
            </ProtectedRoute>
          }
        />

        {/* Unknown routes fall back to the Dashboard */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}