/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author tahaj
 */
public class Podcasts {
    private int id;
    private String title, description;
    private int rating, views;
    private String file, image;
    private int idcategorie;

    public Podcasts() {
    }

    public Podcasts(int id, String title, String description, int rating, int views, String file, String image, int idcategorie) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.views = views;
        this.file = file;
        this.image = image;
        this.idcategorie = idcategorie;
    }

    public Podcasts(String title, String description, int rating, int views, String file, String image, int idcategorie) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.views = views;
        this.file = file;
        this.image = image;
        this.idcategorie = idcategorie;
    }
    
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }
    
    
}
