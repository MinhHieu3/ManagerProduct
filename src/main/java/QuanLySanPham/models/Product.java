package QuanLySanPham.models;

public class Product {
    private int id;
    private String name;
    private double price;
    private Category idCategory;

    public Product() {
    }

    public Product(int id, String name, double price, Category idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }
}
