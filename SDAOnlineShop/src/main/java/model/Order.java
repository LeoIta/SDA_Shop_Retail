package model;

public class Order {
    private int customerID;
    private int DeliveryID;
    private int productID;

    public Order() {
    }

    public Order(int customerID, int deliveryID, int productID) {
        this.customerID = customerID;
        DeliveryID = deliveryID;
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getDeliveryID() {
        return DeliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        DeliveryID = deliveryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
