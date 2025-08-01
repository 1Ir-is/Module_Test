import React from "react";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import BookList from "./components/BookList";
import BookAdd from "./components/BookAdd";

function App() {
  return (
    <div className="container mt-4">
      <Routes>
        <Route path="/" element={<BookList />} />
        <Route path="/add" element={<BookAdd />} />
      </Routes>
    </div>
  );
}

export default App;
