// Direct port of saveHistory()/localStorage history handling
// from the old script.js and iframe/history.html
const HISTORY_KEY = "history";
const MAX_HISTORY_ITEMS = 10;

export function getHistory() {
  return JSON.parse(localStorage.getItem(HISTORY_KEY)) || [];
}

export function saveHistory(text) {
  const history = getHistory();

  history.push(text);

  if (history.length > MAX_HISTORY_ITEMS) {
    history.shift();
  }

  localStorage.setItem(HISTORY_KEY, JSON.stringify(history));
}