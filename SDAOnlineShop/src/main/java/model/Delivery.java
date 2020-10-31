package model;

public class Delivery {
    private int DeliveryID;
    private String name;
    private int deliveryCost;

    public Delivery(String name, int deliveryCost) {
        this.name = name;
        this.deliveryCost = deliveryCost;
    }

    public int getDeliveryID() {
        return this.DeliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        DeliveryID = deliveryID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeliveryCost() {
        return this.deliveryCost;
    }

    public void setDeliveryCost(int deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
