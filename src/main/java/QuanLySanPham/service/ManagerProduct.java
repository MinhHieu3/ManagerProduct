package QuanLySanPham.service;

import QuanLySanPham.models.Category;
import QuanLySanPham.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerProduct implements ProductService {
ManagerCategory managerCategory=new ManagerCategory();
    protected Connection getConnection() {
        Connection connection = null;
        try {
            java.lang.Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/category?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price =rs.getDouble("price");
                int idCategory=rs.getInt("idCategory");
                Category category =managerCategory.findById(idCategory);
                products.add(new Product(id, name,price,category));
            }
        } catch (SQLException e) {
        }
        return products;
    }

    @Override
    public void add(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into product( id,name, price, idCategory) values (?,?,?,?)")) {
            preparedStatement.setInt(1,product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getIdCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Product findById(int id) {
        Product product=new Product();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id =?")) {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int ids = rs.getInt("id");
                String name = rs.getString("name");
                double price =rs.getDouble("price");
                int idCategory=rs.getInt("idCategory");
                Category category =managerCategory.findById(idCategory);
                product=new Product(ids, name,price,category);
            }
        } catch (SQLException e) {
        }
        return product;

    }

    @Override
    public boolean update(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update product set name =? , price=? , idCategory =? where id =?; ")) {
            preparedStatement.setInt(4,product.getId());
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getIdCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?");) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
        return false;
    }

}
