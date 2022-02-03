<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="be.technifutur.demoServlets.models.Produit" %>
<%@ page import="be.technifutur.demoServlets.services.ProduitService" %>
<%@ page import="be.technifutur.demoServlets.dataAccess.ProduitDAO" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="static be.technifutur.demoServlets.utils.Util.escapeSpecialCharacters" %>

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
            <a class="btn" href="<%= request.getContextPath() %>/produit/add">Ajouter</a>
            <a class="btn" href="<%= request.getContextPath() %>/produit/update">Modifier</a>
            <a class="btn" href="<%= request.getContextPath() %>/produit/delete">Supprimer</a>
        </div>
<%@include file="/WEB-INF/jsp/foot.jsp" %>