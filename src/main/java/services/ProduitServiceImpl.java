package services;

import models.Produit;
import models.ProduitForm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProduitServiceImpl implements ProduitService {

    // region SINGLETON
    private static ProduitServiceImpl _instance;

    public static ProduitServiceImpl getInstance() {
        return _instance == null ? _instance = new ProduitServiceImpl() : _instance;
    }

    private ProduitServiceImpl() {
        produits.add(new Produit(1, "patate", "les bons legumes", 9.99));
        produits.add(new Produit(2, "tomate", "les bons legumes", 8.99));
    }
    //endregion

    private final List<Produit> produits = new ArrayList<>();

    @Override
    public List<Produit> getAll() {
        return produits;
    }

    @Override
    public Produit getOne(int id) {
        return produits.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean insert(Produit toAdd) {
        boolean alreadyExists = produits.stream()
                .anyMatch(p -> p.getId() == toAdd.getId());
        if (!alreadyExists) {
            produits.add(toAdd);
        }
        return !alreadyExists;
    }

    @Override
    public Produit delete(int id) {
        Produit deleted = getOne(id);
        if (deleted != null) {
            produits.remove(deleted);
        }
        return deleted;
    }

    @Override
    public List<Produit> getAllSorted(Comparator<Produit> comparator) {
        return produits.stream()
                .sorted(comparator)
                .toList();
    }

    @Override
    public Produit getCheapest() {
        return produits.stream()
                .min(Comparator.comparingDouble(Produit::getPrix))
                .orElse(null);
    }

    @Override
    public Produit getMostExpensive() {
        return produits.stream()
                .max(Comparator.comparingDouble(Produit::getPrix))
                .orElse(null);
    }

    @Override
    public List<Produit> getAllByBrand(String brand) {
        return produits.stream()
                .filter(p -> p.getMarque().equals(brand))
                .toList();
    }

    @Override
    public void update(int id, ProduitForm form) throws IllegalArgumentException {
        Produit p = delete(id);

        try {
            p.setNom(form.getNom());
            p.setPrix(form.getPrix());
            insert(p);
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException("id non valide");
        }
    }
}
