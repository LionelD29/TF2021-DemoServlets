package services;

import models.Produit;
import models.ProduitForm;

import java.util.Comparator;
import java.util.List;

public interface ProduitService {
    List<Produit> getAll();

    Produit getOne(int id);

    /**
     * Ajoute un produit si son id n'est partagé par un produit existant.
     * Pas de null autorisé
     * @param toAdd le produit à ajouter
     * @return <code>true</code> si le produit est ajouté; <code>false</code> sinon
     */
    boolean insert(Produit toAdd);

    Produit delete(int id);

    List<Produit> getAllSorted(Comparator<Produit> comparator);

    Produit getCheapest();

    Produit getMostExpensive();

    List<Produit> getAllByBrand(String brand);

    void update(int id, ProduitForm form);
}