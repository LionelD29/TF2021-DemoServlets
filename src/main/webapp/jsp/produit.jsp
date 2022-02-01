<%@ page contentType="text/html;" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Produit" %>
<%@ page import="services.ProduitService" %>
<%@ page import="dataAccess.ProduitDAO" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="static utils.Util.escapeSpecialCharacters" %>

<%@include file="/WEB-INF/jsp/headProduit.jsp" %>
        <h2>Liste des produits</h2>
        <ul>

        <%
            ProduitService service = ProduitDAO.getInstance();
            List<Produit> list = service.getAll()
                                        .stream()
                                        .sorted(Comparator.comparingInt(Produit::getId))
                                        .toList();
        %>


        <% for( Produit produit : list) { %>
                <li><%= produit.getId() %> | <%= escapeSpecialCharacters(produit.getNom()) %> | <%= escapeSpecialCharacters(produit.getMarque()) %> | <%= produit.getPrix() %> EUR</li>
        <% } %>

        </ul>
        <div class="options">
            <a class="btn" href="/demoServlets/produit/add">Ajouter</a>
            <a class="btn" href="/demoServlets/produit/update">Modifier</a>
            <a class="btn" href="/demoServlets/produit/delete">Supprimer</a>
        </div>
<%@include file="/WEB-INF/jsp/foot.jsp" %>