<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="be.technifutur.demoServlets.models.Magasin" %>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/produits.css">
        <title>MonApp</title>
    </head>
    <body>
        <div class="container">
            <header>
               <h1>MonApp</h1>
               <%
                Magasin magasin = (Magasin) request.getAttribute("magasin");
               %>
               <nav>
                   <a href="<%= request.getContextPath() %>/magasin/detail?id=<%= magasin.getId() %>">Retour</a>
               </nav>
            </header>
            <hr>
            <h2>Ajout d'un produit</h2>
            <form action="<%= request.getContextPath() %>/magasin/addProduit" method="post">
                <input type="hidden" name="magasinId" value="<%= magasin.getId() %>">
                <input type="number" name="produitId" placeholder="Id du produit" required autofocus><br>
                <input type="text" name="nom" placeholder="Nom" maxlength="30" required><br>
                <input type="text" name="marque" placeholder="Marque" maxlength="30" required><br>
                <input type="text" name="prix" placeholder="Prix" required><br>
                <input class="btn" type="submit" value="Ajouter">
            </form>
<%@include file="/WEB-INF/jsp/foot.jsp" %>