package QuanLySanPham.controller;

import QuanLySanPham.models.Category;
import QuanLySanPham.models.Product;
import QuanLySanPham.service.ManagerCategory;
import QuanLySanPham.service.ManagerProduct;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "category", value = "/category")
public class ServletCategory extends HttpServlet {

    ManagerCategory managerCategory = new ManagerCategory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":

                break;
            case "edit":

                break;
            case "delete":

                break;
            default:
                showListCategory(req, resp);
        }
    }

    private void showListCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("category/list.jsp");
        List<Category> list = managerCategory.findAll();
        req.setAttribute("danhSach", list);
        requestDispatcher.forward(req, resp);
    }
}
