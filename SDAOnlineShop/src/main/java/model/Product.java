package model;

public class Product {
    private int productID;
    private String color;
    private String size;
    private String productCode;
    private int price;
    private int orderID;

    public Product(String color, String size, String productCode, int price) {
        this.color = color;
        this.size = size;
        this.productCode = productCode;
        this.price = price;
    }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
