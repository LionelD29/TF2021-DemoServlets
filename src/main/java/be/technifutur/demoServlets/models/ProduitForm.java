package be.technifutur.demoServlets.models;

public class ProduitForm {

    private String nom;
    private String marque;
    private double prix;

    public ProduitForm() {
    }

    public ProduitForm(String nom, double prix, String marque) {
        this.nom = nom;
        this.prix = prix;
        this.marque = marque;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}