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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "product", value = "/products")
public class ServlerProduct extends HttpServlet {
    ManagerProduct managerProduct=new ManagerProduct();

    ManagerCategory managerCategory=new ManagerCategory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showListCreate(req, resp);
                break;
            case "edit":
                showListEdit(req, resp);
                break;
            case "delete":
                try {
                    showListDelete(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                Search(req,resp);
                break;
            default:
                showListProduct(req, resp);
        }
    }

    private void Search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


    private void showListDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id= Integer.parseInt(req.getParameter("id"));
        managerProduct.delete(id);
        resp.sendRedirect("/products");

    }

    private void showListEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("managerProduct/editProduct.jsp");
        List<Category> categories =managerCategory.findAll();
        req.setAttribute("danhSach",categories);
        requestDispatcher.forward(req,resp);
    }

    private void showListCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("managerProduct/createProduct.jsp");
        List<Category> categories =managerCategory.findAll();
        req.setAttribute("danhSach",categories);
        requestDispatcher.forward(req,resp);

    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("managerProduct/listProduct.jsp");
        List<Product> products=managerProduct.findAll();
        req.setAttribute("danhSach",products);
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    create(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "edit":
                try {
                    edit(req, resp);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }




    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id= Integer.parseInt(req.getParameter("id"));
        String name =req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int category= Integer.parseInt(req.getParameter("category"));
        Category cate=managerCategory.findById(category);
        managerProduct.update(new Product(id,name,price,cate));
        resp.sendRedirect("/products");
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        int id= Integer.parseInt(req.getParameter("id"));
        String name =req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int category= Integer.parseInt(req.getParameter("category"));
        Category cate=managerCategory.findById(category);
        managerProduct.add(new Product(id,name,price,cate));
        resp.sendRedirect("/products");
    }
}
