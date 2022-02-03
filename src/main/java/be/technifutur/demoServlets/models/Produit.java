package be.technifutur.demoServlets.models;

public class Produit {
    private int id;
    private String nom;
    private String marque;
    private double prix;

    public Produit() {
    }

    public Produit(String nom, String marque, double prix) {
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
    }

    public Produit(int id, String nom, String marque, double prix) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", marque='" + marque + '\'' +
                ", prix=" + prix +
                '}';
    }
}
