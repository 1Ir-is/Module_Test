import axios from "axios";

const API_URL = "http://localhost:3001/books";

export async function getAll() {
  const response = await axios.get(API_URL);
  return response.data;
}

export async function getById(id) {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
}

export async function addNew(book) {
  const response = await axios.post(API_URL, book);
  return response.data;
}
