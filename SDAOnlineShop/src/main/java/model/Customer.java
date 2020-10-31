package model;

public class Customer {
    private int customerId;
    private String first_name;
    private String last_name;
    private String mail;
    private String telephone;
    private int addressId;
    private int accountId;

    public Customer(String first_name, String last_name, String mail, String telephone, int addressId) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.telephone = telephone;
        this.addressId = addressId;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAddressId() {
        return this.addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
