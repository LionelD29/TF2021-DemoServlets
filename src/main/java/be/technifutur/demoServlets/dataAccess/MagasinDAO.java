package be.technifutur.demoServlets.dataAccess;

import be.technifutur.demoServlets.models.Magasin;
import be.technifutur.demoServlets.models.Produit;
import be.technifutur.demoServlets.services.MagasinService;

import java.sql.*;
import java.util.ArrayList;
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
        magasins = getAll();
    }
    // endregion

    private List<Magasin> magasins = new ArrayList<>();

    @Override
    public List<Magasin> getAll() {

        List<Magasin> list;
        String query = """
                SELECT *
                FROM stores
                """;

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            list = new ArrayList<>();
            while (rs.next()) {
                Magasin m = new Magasin(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("postalCode"),
                        rs.getInt("number"),
                        rs.getInt("area")
                );

                // TODO: finir la liaison entre la BDD des magasins et celle des produits
                /*m.setProduitsDispo(
                        getAllProduits(m.getId())
                );*/
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
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
        boolean insertResult = false;
        String query = """
                CALL SP_INSERT_MAGASIN(?, ?, ?, ?, ?, ?, ?)
                """;

        try (
            Connection co = ConnectionFactory.getConnection();
            CallableStatement stmt = co.prepareCall(query)
        ) {
            stmt.setString(1, toAdd.getNom());
            stmt.setString(2, toAdd.getRue());
            stmt.setString(3, toAdd.getVille());
            stmt.setString(4, toAdd.getCodePostal());
            stmt.setInt(5, toAdd.getNumero());
            stmt.setInt(6, toAdd.getSuperficie());

            // Ajoute le magasin dans la BDD
            stmt.execute();

            // Ajoute aussi dans la liste en interne du service
            toAdd.setId(stmt.getInt("insertedId"));
            magasins.add(toAdd);

            insertResult = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertResult;
    }

    // TODO: Même chose
//    public List<Produit> getAllProduits(int magasinId) {
//        return null;
//    }

    @Override
    public boolean insertProduct(int idMagasin, Produit toAdd) {
        boolean insertResult = false;
        // TODO: Ajouter le produit dans la table pivot de la BDD
        // D'abord vérifier si le produit existe déjà dans la table products
        // Sinon, renvoyer un message pour dire de d'abord créer le produit

        // Une fois cela fait, ajouter dans la table pivot :
        // INSERT INTO store_product_pivot VALUES (idMagasin, toAdd.getId());
        // Si ça se passe bien, ajouter alors le produit en interne

        // Pour ajouter le produit en interne
        Magasin magasin = getOne(idMagasin);
        if (magasin != null) {
            insertResult = magasin.insertProduct(toAdd);
        }
        return insertResult;
    }

    @Override
    public Produit removeProduct(int idMagasin, int idProduit) {
        Produit p = null;
        // TODO: Supprimer le produit dans la table pivot de la BDD
        // D'abord vérifier s'il existe bien une ligne correspondante dans la table pivot
        // Sinon, renvoyer un message d'erreur

        // Une fois cela fait, supprimer dans la table pivot :
        // DELETE FROM store_product_pivot WHERE store_id = idMagasin AND product_id = idProduit;
        // Si ça se passe bien, supprimer alors le produit en interne

        // Pour supprimer le produit en interne
        Magasin magasin = getOne(idMagasin);
        if (magasin != null) {
            p = magasin.deleteProduct(idProduit);
        }
        return p;
    }
}
