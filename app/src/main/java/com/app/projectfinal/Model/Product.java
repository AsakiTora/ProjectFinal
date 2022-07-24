package com.app.projectfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String id, id_shop, id_category, id_unit, quantity, price, name, description, status, date_create, shop_name, category_name, unit_name;
    private List<Object> image;

    public Product(String id, String id_shop, String id_category, String id_unit, String quantity, String price, String name, String description, String status, String date_create, String shop_name, String category_name, String unit_name, List<Object> image) {
        this.id = id;
        this.id_shop = id_shop;
        this.id_category = id_category;
        this.id_unit = id_unit;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.description = description;
        this.status = status;
        this.date_create = date_create;
        this.shop_name = shop_name;
        this.category_name = category_name;
        this.unit_name = unit_name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_shop() {
        return id_shop;
    }

    public void setId_shop(String id_shop) {
        this.id_shop = id_shop;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getId_unit() {
        return id_unit;
    }

    public void setId_unit(String id_unit) {
        this.id_unit = id_unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String info) {
        this.description = description;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Object> getImage() {
        return image;
    }

    public void setImage(List<Object> image) {
        this.image = image;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setStore_name(String store_name) {
        this.shop_name = shop_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }
}
