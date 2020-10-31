package model;

public class Delivery {
    private String name;
    private int deliveryCost;

    public Delivery(String name, int deliveryCost) {
        this.name = name;
        this.deliveryCost = deliveryCost;
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
