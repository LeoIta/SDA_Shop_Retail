package model;

public class Order {
    private int customerId;
    private int deliveryId;
    private int productId;

    public Order() {
    }

    public Order(int customerId, int deliveryId, int productId) {
        this.customerId = customerId;
        deliveryId = deliveryId;
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryID(int deliveryId) {
        deliveryId = deliveryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
