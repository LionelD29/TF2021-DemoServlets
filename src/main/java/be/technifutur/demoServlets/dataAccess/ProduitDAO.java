package be.technifutur.demoServlets.dataAccess;

import be.technifutur.demoServlets.models.Produit;
import be.technifutur.demoServlets.models.ProduitForm;
import be.technifutur.demoServlets.services.ProduitService;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProduitDAO implements ProduitService {

    // region SINGLETON
    private static ProduitDAO _instance;

    private ProduitDAO() {
        initList();
    }

    public static ProduitDAO getInstance() {
        return _instance == null ? _instance = new ProduitDAO() : _instance;
    }

    private void initList() {
        produits = getAll();
    }
    // endregion

    private List<Produit> produits = new ArrayList<>();

    // CREATE
    @Override
    public boolean insert(Produit toAdd) {
        boolean insertResult = false;
        String query = """
                CALL SP_INSERT_PRODUCT(?, ?, ?, ?)
                """;

        try (
            Connection co = ConnectionFactory.getConnection();
            CallableStatement stmt = co.prepareCall(query)
        ) {
            stmt.setString(1, toAdd.getNom());
            stmt.setString(2, toAdd.getMarque());
            stmt.setDouble(3, toAdd.getPrix());

            stmt.execute();
            toAdd.setId(stmt.getInt("insertedId"));
            produits.add(toAdd);
            insertResult = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertResult;
    }

    // READ
    @Override
    public Produit getOne(int id) {
        Produit p = null;
        String query = "SELECT *" +
                " FROM products" +
                " WHERE id = " + id;

        try (
            Connection co = ConnectionFactory.getConnection();
            Statement stmt = co.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        ) {
            if (rs.next()) {
                p = new Produit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> list;
        String query = """
                SELECT *
                FROM products
                """;

        try (
                Connection co = ConnectionFactory.getConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            list = new ArrayList<>();
            while (rs.next()) {
                Produit p = new Produit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getDouble("price")
                );

                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
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
    public List<Produit> getAllSorted(Comparator<Produit> comparator) {
        return produits.stream()
                .sorted(comparator)
                .toList();
    }

    // UPDATE
    @Override
    public void update(int id, ProduitForm form) throws IllegalArgumentException {
        String query = """
                UPDATE products
                SET name = ?, brand = ?, price = ?
                WHERE id = ?
                """;

        try (
            Connection co = ConnectionFactory.getConnection();
            PreparedStatement stmt = co.prepareStatement(query)
        ) {
            // Récupère l'élément qui doit être modifié
            Produit toModify = produits.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst()
                    .orElse(null);
            Produit newProduct;

            if (toModify != null) {
                String name = form.getNom();
                String brand = form.getMarque();
                double prix = form.getPrix();

                // Si l'un des champs du formulaire est vide (sauf l'id et le prix),
                // on met alors l'ancienne valeur, sinon on met la nouvelle
                if (name != null && !name.isBlank()) {
                    stmt.setString(1, name);
                } else {
                    stmt.setString(1, toModify.getNom());
                }

                if (brand != null && !brand.isBlank()) {
                    stmt.setString(2, brand);
                } else {
                    stmt.setString(2, toModify.getMarque());
                }

                stmt.setDouble(3, form.getPrix());
                stmt.setInt(4, id);

                if (1 == stmt.executeUpdate()) {
                    newProduct = getOne(id);
                    produits.set(produits.indexOf(toModify), newProduct);
                    System.out.println("Modification réussie");
                } else {
                    System.out.println("Modification échouée");
                }
            } else {
                throw new IllegalArgumentException("id non valide");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    @Override
    public Produit delete(int id) {
        Produit deletedProduct = null;
        String query = "DELETE" +
                       " FROM products" +
                       " WHERE id = " + id;

        try (
            Connection co = ConnectionFactory.getConnection();
            Statement stmt = co.createStatement()
        ) {
            if (1 == stmt.executeUpdate(query)) {
                deletedProduct = produits.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst()
                        .orElse(null);
                if (deletedProduct != null) {
                    produits.remove(deletedProduct);
                }
            } else {
                System.out.println("Suppression échouée");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deletedProduct;
    }
}
