package com.sohail.wheeler.modal;

public class Cart {

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    private String userid;
    private String productid;
    private String name;
    private String price;
    private String description;
    private String specification;

    public Cart(String userid, String productid, String name, String price, String description, String specification) {
        this.userid = userid;
        this.productid = productid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.specification = specification;
    }


}
