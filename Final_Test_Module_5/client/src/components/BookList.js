import { useEffect, useState } from "react";
import { getAll } from "../services/bookService";
import { getAllCategories } from "../services/categoryService";
import { Link } from "react-router-dom";

function BookList() {
  const [books, setBooks] = useState([]);
  const [categories, setCategories] = useState([]);
  const [searchTitle, setSearchTitle] = useState("");
  const [searchCategory, setSearchCategory] = useState("");

  const fetchBooks = async () => {
    const data = await getAll();
    const sortedBooks = data.sort((a, b) => b.quantity - a.quantity);
    setBooks(sortedBooks);
  };

  const fetchCategories = async () => {
    const data = await getAllCategories();
    setCategories(data);
  };

  useEffect(() => {
    fetchBooks();
    fetchCategories();
  }, []);

  const getCategoryById = (categoryId) => {
    return categories.find((cat) => cat.id === categoryId);
  };

  const filtered = books.filter((book) => {
    const titleMatch = book.title
      .toLowerCase()
      .includes(searchTitle.toLowerCase());
    const categoryMatch =
      !searchCategory || book.categoryId.toString() === searchCategory;
    return titleMatch && categoryMatch;
  });
  return (
    <div className="container-fluid p-4">
      <h2>Danh sách quyển sách</h2>
      <div className="mb-3 d-flex gap-2">
        <input
          type="text"
          className="form-control"
          style={{ maxWidth: 250 }}
          placeholder="Tìm theo tên sách..."
          value={searchTitle}
          onChange={(e) => setSearchTitle(e.target.value)}
        />
        <select
          className="form-control"
          style={{ maxWidth: 250 }}
          value={searchCategory}
          onChange={(e) => setSearchCategory(e.target.value)}
        >
          <option value="">Tất cả thể loại</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.categoryName}
            </option>
          ))}
        </select>
      </div>
      <Link to="/add" className="btn btn-success mb-2">
        Thêm mới sách
      </Link>

      {filtered.length === 0 ? (
        <div className="text-center mt-5"> S
          <h4 className="text-muted">Không có thông tin sách này!</h4>
        </div>
      ) : (
        <table className="table table-bordered">
          <thead>
            <tr>
              <th>Mã sách</th>
              <th>Tên sách</th>
              <th>Thể loại</th>
              <th>Mô tả thể loại</th>
              <th>Ngày nhập sách</th>
              <th>Số lượng</th>
            </tr>
          </thead>
          <tbody>
            {filtered.map((book) => {
              const category = getCategoryById(book.categoryId);
              return (
                <tr key={book.id}>
                  <td>{book.bookCode}</td>
                  <td>{book.title}</td>
                  <td>{category?.categoryName}</td>
                  <td>{category?.description}</td>
                  <td>{book.importDate}</td>
                  <td>{book.quantity}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default BookList;
