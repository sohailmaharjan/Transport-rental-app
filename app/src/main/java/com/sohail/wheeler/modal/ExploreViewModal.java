package com.sohail.wheeler.modal;

import java.io.Serializable;

public class ExploreViewModal implements Serializable {

    private String name;
    private String price;
    private String image;
    private String description;
    private String specification;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    private String _id;

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public ExploreViewModal(String name, String price, String image, String description, String specification, String _id) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.specification = specification;
        this._id=_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "tbl_product [name = " + name + ", price = " + price + "," +
                " description = " + description + ",  image = " + image + "]";
    }
}
