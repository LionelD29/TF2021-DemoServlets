<%@ page contentType="text/html;" %>

<%@include file="/WEB-INF/jsp/headAddDeleteUpdateProduit.jsp" %>
        <h2>Ajout d'un produit</h2>
        <form action="<%= request.getContextPath() %>/produit/add" method="post">
            <input type="text" name="nom" placeholder="Nom" maxlength="30" autofocus><br>
            <input type="text" name="marque" placeholder="Marque" maxlength="30"><br>
            <input type="text" name="prix" placeholder="Prix" required><br>
            <input class="btn" type="submit" value="Envoyer">
        </form>
<%@include file="/WEB-INF/jsp/footProduit.jsp" %>