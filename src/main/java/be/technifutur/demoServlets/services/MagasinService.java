package be.technifutur.demoServlets.services;

import be.technifutur.demoServlets.models.Magasin;
import be.technifutur.demoServlets.models.Produit;

import java.util.List;

public interface MagasinService {

    List<Magasin> getAll();

    Magasin getOne(int id);

    boolean insert(Magasin toAdd);

    boolean insertProduct(int idMagasin, int toProduit);

    Produit removeProduct(int idMagasin, int idProduit);
}
