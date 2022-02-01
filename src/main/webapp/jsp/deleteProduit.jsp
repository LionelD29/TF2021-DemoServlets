<%@ page contentType="text/html;" %>

<%@include file="/WEB-INF/jsp/headAddDeleteUpdateProduit.jsp" %>
        <h2>Suppression d'un produit</h2>
        <form action="<%= request.getContextPath() %>/produit/delete" method="post">
            <input type="number" name="id" placeholder="Id" required autofocus><br>
            <input class="btn" type="submit" value="Supprimer">
        </form>
<%@include file="/WEB-INF/jsp/foot.jsp" %>