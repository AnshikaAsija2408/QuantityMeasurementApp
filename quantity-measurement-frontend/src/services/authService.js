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

export function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("history");
  window.location.href = BACKEND_URL + "/logout";
}