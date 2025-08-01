import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { addNew } from "../services/bookService";
import { getAllCategories } from "../services/categoryService";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect, useState } from "react";

const bookValidationSchema = Yup.object().shape({
  bookCode: Yup.string()
    .matches(/^BO-\d{4}$/, "Mã sách phải theo định dạng BO-XXXX")
    .required("Mã sách không được để trống!"),
  title: Yup.string()
    .max(100, "Tên sách không được dài quá 100 ký tự")
    .required("Tên sách không được để trống!"),
  categoryId: Yup.number().required("Vui lòng chọn thể loại!"),
  importDate: Yup.date()
    .max(new Date(), "Ngày nhập không được lớn hơn ngày hiện tại")
    .required("Ngày nhập không được để trống!"),
  quantity: Yup.number()
    .integer("Số lượng sách phải là số nguyên")
    .min(1, "Số lượng sách phải lớn hơn 0")
    .required("Số lượng sách không được để trống!"),
});

function BookAdd() {
  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    async function fetchCategories() {
      const data = await getAllCategories();
      setCategories(data);
    }
    fetchCategories();
  }, []);

  const formatDateForSave = (dateString) => {
    return dateString.split("-").reverse().join("/");
  };

  return (
    <div className="card">
      <div className="card-header">Thêm mới sách</div>
      <div className="card-body">
        <Formik
          initialValues={{
            bookCode: "",
            title: "",
            categoryId: "",
            importDate: "",
            quantity: "",
          }}
          validationSchema={bookValidationSchema}
          onSubmit={async (values) => {
            const bookData = {
              ...values,
              categoryId: Number(values.categoryId),
              quantity: Number(values.quantity),
              importDate: formatDateForSave(values.importDate),
            };
            await addNew(bookData);
            toast.success("Thêm mới sách thành công!");
            navigate("/");
          }}
        >
          <Form>
            <div className="mb-3">
              <label>Mã sách</label>
              <Field
                name="bookCode"
                className="form-control"
                placeholder="BO-XXXX"
              />
              <ErrorMessage
                name="bookCode"
                component="div"
                className="text-danger"
              />
            </div>

            <div className="mb-3">
              <label>Tên sách</label>
              <Field name="title" className="form-control" maxLength={100} />
              <ErrorMessage
                name="title"
                component="div"
                className="text-danger"
              />
            </div>

            <div className="mb-3">
              <label>Thể loại</label>
              <Field as="select" name="categoryId" className="form-select">
                <option value="">--Chọn thể loại--</option>
                {categories.map((category) => (
                  <option key={category.id} value={category.id}>
                    {category.categoryName}
                  </option>
                ))}
              </Field>
              <ErrorMessage
                name="categoryId"
                component="div"
                className="text-danger"
              />
            </div>

            <div className="mb-3">
              <label>Ngày nhập sách</label>
              <Field
                name="importDate"
                type="date"
                className="form-control"
                max={new Date().toISOString().split("T")[0]}
              />
              <ErrorMessage
                name="importDate"
                component="div"
                className="text-danger"
              />
            </div>

            <div className="mb-3">
              <label>Số lượng</label>
              <Field
                name="quantity"
                type="number"
                className="form-control"
                min="1"
                step="1"
              />
              <ErrorMessage
                name="quantity"
                component="div"
                className="text-danger"
              />
            </div>

            <button type="submit" className="btn btn-primary">
              Thêm mới
            </button>
            <button
              type="button"
              className="btn btn-secondary ms-2"
              onClick={() => navigate("/")}
            >
              Huỷ
            </button>
          </Form>
        </Formik>
      </div>
    </div>
  );
}

export default BookAdd;
