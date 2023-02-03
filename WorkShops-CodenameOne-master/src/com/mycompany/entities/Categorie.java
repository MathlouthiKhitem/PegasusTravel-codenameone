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
public class Categorie {
    private int idcategorie;
    private String namecateg, descriptioncateg;

    public Categorie(int idcategorie, String namecateg, String descriptioncateg) {
        this.idcategorie = idcategorie;
        this.namecateg = namecateg;
        this.descriptioncateg = descriptioncateg;
    }

    public Categorie(String namecateg, String descriptioncateg) {
        this.namecateg = namecateg;
        this.descriptioncateg = descriptioncateg;
    }

    public Categorie() {
    }

    public int getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNamecateg() {
        return namecateg;
    }

    public void setNamecateg(String namecateg) {
        this.namecateg = namecateg;
    }

    public String getDescriptioncateg() {
        return descriptioncateg;
    }

    public void setDescriptioncateg(String descriptioncateg) {
        this.descriptioncateg = descriptioncateg;
    }
    
    
}
