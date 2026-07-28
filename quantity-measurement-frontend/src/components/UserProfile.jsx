import React from "react";
import { getCurrentUserId } from "../services/authService.js";

export default function UserProfile() {

  const userId = getCurrentUserId();

  if (!userId) return null;

  const initial = userId.charAt(0).toUpperCase();

  return (
    <div className="user-profile">

      <div className="user-profile-avatar">
        {initial}
      </div>

      <div className="user-profile-card">

        <div className="user-profile-card-avatar">
          {initial}
        </div>

        <div className="user-profile-card-info">

          <div className="user-profile-card-label">
            Signed in as
          </div>

          <div className="user-profile-card-email">
            {userId}
          </div>

        </div>

      </div>

    </div>
  );
}