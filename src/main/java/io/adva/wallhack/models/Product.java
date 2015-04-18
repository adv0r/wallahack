package io.adva.wallhack.models;

/**
 * Created by admin on 18/04/15.
 */
public class Product {

    private String title;
    private String description;
    private String url;
    private double price;
    private String category;
    private String location;
    private String imgURL;
    private String sellerURL;

    public Product(String title, String description, String url, double price, String category,String location, String imgUrl,String seller) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.price = price;
        this.category = category;
        this.location = location;
        this.imgURL = imgUrl;
        this.sellerURL = seller;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getSellerURL() {
        return sellerURL;
    }

    public void setSellerURL(String sellerURL) {
        this.sellerURL = sellerURL;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", imgURL='" + imgURL + '\'' +
                ", sellerURL='" + sellerURL + '\'' +
                '}';
    }
}
