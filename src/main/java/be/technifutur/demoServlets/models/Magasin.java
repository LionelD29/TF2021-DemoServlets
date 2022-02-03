package be.technifutur.demoServlets.models;

import java.util.ArrayList;
import java.util.List;

public class Magasin {
    private int id;
    private String nom;
    private String rue;
    private String ville;
    private String codePostal;
    private int numero;
    private int superficie;
    private List<Produit> produitsDispo = new ArrayList<>();

    public Magasin() {
    }

    public Magasin(String nom, String rue, String ville, String codePostal, int numero, int superficie) {
        this.nom = nom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numero = numero;
        this.superficie = superficie;
    }

    public Magasin(int id, String nom, String rue, String ville, String codePostal, int numero, int superficie) {
        this.id = id;
        this.nom = nom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numero = numero;
        this.superficie = superficie;
    }

    // region getters_setters
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

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public List<Produit> getProduitsDispo() {
        return produitsDispo;
    }

    public void setProduitsDispo(List<Produit> produitsDispo) {
        this.produitsDispo = produitsDispo;
    }
    // endregion

    public boolean insertProduct(Produit produit) {
        boolean alreadyPresent = produitsDispo.stream()
                .anyMatch(p -> p.getId() == produit.getId());

        if (!alreadyPresent) {
            produitsDispo.add(produit);
        }
        return !alreadyPresent;
    }

    public Produit deleteProduct(int productId) {
        Produit deletedProduct = produitsDispo.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);

        if (deletedProduct != null) {
            produitsDispo.remove(deletedProduct);
        }
        return deletedProduct;
    }

    @Override
    public String toString() {
        return "Magasin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", rue='" + rue + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", numero=" + numero +
                ", superficie=" + superficie +
                ", produitsDispo=" + produitsDispo +
                '}';
    }
}
