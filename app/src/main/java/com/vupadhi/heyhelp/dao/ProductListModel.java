package com.vupadhi.heyhelp.dao;

public class ProductListModel {
    int ProductID, Price, quantity;
    String Text, ImageURL, Description;

    public ProductListModel(int productID, int price, int quantity, String text, String imageURL, String description) {
        this.ProductID = productID;
        this.Price = price;
        this.quantity = quantity;
        this.Text = text;
        this.ImageURL = imageURL;
        this.Description = description;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
