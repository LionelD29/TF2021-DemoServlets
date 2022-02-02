package services;

import models.Magasin;
import models.Produit;

import java.util.List;

public interface MagasinService {

    List<Magasin> getAll();

    Magasin getOne(int id);

    boolean insert(Magasin toAdd);

    boolean insertProduct(int idMagasin, Produit toAdd);

    Produit removeProduct(int idMagasin, int idProduit);
}
