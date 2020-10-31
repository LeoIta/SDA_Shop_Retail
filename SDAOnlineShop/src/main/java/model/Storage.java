package model;

public class Storage {
    private String productCode;
    private int available_quantity;

    public Storage(String productCode, int available_quantity) {
        this.productCode = productCode;
        this.available_quantity = available_quantity;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getAvailable_quantity() {
        return this.available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }
}
