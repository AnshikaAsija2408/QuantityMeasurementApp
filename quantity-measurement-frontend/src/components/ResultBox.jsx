import React from "react";

// result shape: { status: 'idle' | 'loading' | 'success' | 'error', type?: 'text' | 'heading', content?: string|number }
export default function ResultBox({ result }) {
  const { status, type, content } = result;

  let body;

  if (status === "loading") {
    body = "Calculating...";
  } else if (status === "error") {
    body = content;
  } else if (status === "success") {
    body = type === "heading" ? <h2>{content}</h2> : content;
  } else {
    body = "Waiting for calculation...";
  }

  return (
    <section className="card">
      <h2>Result</h2>
      <div id="resultBox">{body}</div>
    </section>
  );
}