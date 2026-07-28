const BACKEND_URL = "http://localhost:8080";

export function captureTokenFromUrl() {
  const params = new URLSearchParams(window.location.search);
  const jwtToken = params.get("token");

  if (jwtToken) {
    localStorage.setItem("token", jwtToken);

    // clean the "?token=..." off the URL, keep current path ("/")
    window.history.replaceState({}, document.title, window.location.pathname);
    return true;
  }

  return false;
}

export function getToken() {
  return localStorage.getItem("token");
}

export function isAuthenticated() {
  return !!getToken();
}

// Decodes the existing JWT (already stored in localStorage after
// Google OAuth sign-in) to read the logged-in user's identity.
// No new auth flow is introduced — this simply reads the "sub"
// claim (the user's email) that the backend already puts in the
// token via JwtUtil.generateToken().
export function getCurrentUserId() {
  const token = getToken();

  if (!token) {
    return null;
  }

  try {
    const payloadBase64 = token.split(".")[1];
    const payloadJson = atob(
      payloadBase64.replace(/-/g, "+").replace(/_/g, "/")
    );
    const payload = JSON.parse(payloadJson);
    return payload.sub || null;
  } catch (e) {
    return null;
  }
}

export function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("history");
  window.location.href = BACKEND_URL + "/logout";
}