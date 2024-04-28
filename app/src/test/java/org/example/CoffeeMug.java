package org.example;

public class CoffeeMug {

    private String tittle;
    private String imageURL;
    private int reviews;

    public CoffeeMug(String tittle, String imageURL, int reviews) {
        this.tittle = tittle;
        this.imageURL = imageURL;
        this.reviews = reviews;
    }

    public String getTittle() {
        return tittle;
    }

    public String getImageUrl() {
        return imageURL;
    }

    public int getReviews() {
        return reviews;
    }

}
