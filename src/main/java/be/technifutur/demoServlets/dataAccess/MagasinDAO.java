package be.technifutur.demoServlets.dataAccess;

import be.technifutur.demoServlets.models.Magasin;
import be.technifutur.demoServlets.models.Produit;
import be.technifutur.demoServlets.services.MagasinService;
import be.technifutur.demoServlets.services.ProduitService;

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

                m.setProduitsDispo(getAllProduits(m.getId()));
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

    public List<Produit> getAllProduits(int magasinId) {
        List<Produit> produits;
        String query = "SELECT p.* " +
                       "FROM store_product_pivot spv " +
                       "    INNER JOIN products p " +
                       "        ON p.id = spv.product_id " +
                       "WHERE spv.store_id = " + magasinId;

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            produits = new ArrayList<>();
            while (rs.next()) {
                Produit p = new Produit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price")
                );

                produits.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            produits = null;
        }
        return produits;
    }

    @Override
    public boolean insertProduct(int idMagasin, int idProduit) {
        boolean insertResult = false;
        Produit p;
        ProduitService serviceProduit = ProduitDAO.getInstance();

        // D'abord vérifier si le produit existe déjà dans la table products
        // Sinon, renvoyer un message pour dire de d'abord créer le produit
        p = serviceProduit.getOne(idProduit);
        if (p == null) {
            System.out.println("Le produit n'existe pas encore");
        } else {
            // Une fois cela fait, ajouter dans la table pivot :
            String query = "INSERT INTO store_product_pivot VALUES (" + idMagasin + ", " + idProduit + ")";
            try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement()
            ) {
                if (1 != stmt.executeUpdate(query)) {
                    System.out.println("Echec de l'insertion du couple magasin / produit dans la table pivot");
                } else {
                    System.out.println("Insertion dans la table pivot réussie");
                    // Si ça se passe bien, ajouter alors le produit en interne
                    Magasin magasin = getOne(idMagasin);
                    if (magasin == null) {
                        System.out.println("Ce magasin n'est pas utilisé dans le programme");
                    } else {
                        insertResult = magasin.insertProduct(p);
                        if (!insertResult) {
                            System.out.println("Id de produit déjà utilisé dans la liste du magasin");
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insertResult;
    }

    @Override
    public Produit removeProduct(int idMagasin, int idProduit) {
        Produit p = null;
        // D'abord vérifier si le magasin passé contient bien ce produit
        // Sinon, renvoyer un message d'erreur

        Magasin magasin = getOne(idMagasin);

        if (magasin == null) {
            System.out.println("id du magasin invalide");
        } else {
            boolean isPresent = magasin.getProduitsDispo()
                                        .stream()
                                        .anyMatch(prod -> prod.getId() == idProduit);

            if (!isPresent) {
                System.out.println("produit non disponible dans le magasin");
            } else {
                // Une fois cela fait, supprimer dans la table pivot :
                String query = "DELETE FROM store_product_pivot WHERE store_id = "
                        + idMagasin + " AND product_id = " + idProduit;

                try (
                        Connection co = ConnectionFactory.getConnection();
                        Statement stmt = co.createStatement()
                ) {
                    if (1 != stmt.executeUpdate(query)) {
                        System.out.println("Suppression dans la table pivot échouée");
                    } else {
                        // Si ça se passe bien, supprimer alors le produit en interne
                        p = magasin.deleteProduct(idProduit);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return p;
    }
}
