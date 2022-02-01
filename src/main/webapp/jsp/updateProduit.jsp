<%@ page contentType="text/html" %>

<%@include file="/WEB-INF/jsp/headAddDeleteUpdateProduit.jsp" %>
        <h2>Modification d'un produit</h2>
        <form action="<%= request.getContextPath() %>/produit/update" method="post">
            <input type="number" name="id" placeholder="Id" required autofocus><br>
            <input type="text" name="nom" placeholder="Nom" maxlength="30"><br>
            <input type="text" name="marque" placeholder="Marque" maxlength="30"><br>
            <input type="text" name="prix" placeholder="Prix" required><br>
            <input class="btn" type="submit" value="Modifier">
        </form>
<%@include file="/WEB-INF/jsp/foot.jsp" %>