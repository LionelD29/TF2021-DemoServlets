package dataAccess;

import models.Magasin;
import models.Produit;
import services.MagasinService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MagasinDAO implements MagasinService {

    // region SINGLETON
    private static MagasinDAO _instance;

    private MagasinDAO() {
        initList();
    }

    public static MagasinDAO getInstance() {
        return _instance == null ? _instance = new MagasinDAO() : _instance;
    }

    private void initList() {
        magasins.add(new Magasin(0,
                "Carrefour",
                "rue",
                "ville",
                "cp",
                "04",
                1000,
                List.of(new Produit("p1", "m1", 1))));
        magasins.add(new Magasin(1, "Delhaize", new Produit("p3", "m3", 3), new Produit("p2", "m2", 2)));
        magasins.add(new Magasin(2, "Cora", new Produit("p4", "m4", 4), new Produit("p5", "m5", 5)));
    }
    // endregion

    private List<Magasin> magasins = new ArrayList<>();

    @Override
    public List<Magasin> getAll() {
        return magasins;
    }

    @Override
    public Magasin getOne(int id) {
        return magasins.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean insert(Magasin toAdd) {
        boolean existingId = magasins.stream()
                .anyMatch(m -> m.getId() == toAdd.getId());

        if (!existingId) {
            magasins.add(toAdd);
        }
        return !existingId;
    }

    @Override
    public boolean insertProduct(int idMagasin, Produit toAdd) {
        boolean insertResult = false;
        Magasin magasin = getOne(idMagasin);

        if (magasin != null) {
            insertResult = magasin.insertProduct(toAdd);
        }
        return insertResult;
    }

    @Override
    public Produit removeProduct(int idMagasin, int idProduit) {
        Produit p = null;
        Magasin magasin = getOne(idMagasin);

        if (magasin != null) {
            p = magasin.deleteProduct(idProduit);
        }
        return p;
    }
}