<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="models.Magasin" %>
<%@ page import="models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>

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
               <nav>
                   <a href="<%= request.getContextPath() %>/magasin">Retour</a>
               </nav>
            </header>
            <hr>
            <%
                Magasin magasin = (Magasin) request.getAttribute("magasin");
                List<Produit> produits = magasin.getProduitsDispo()
                                                .stream()
                                                .sorted(Comparator.comparingInt(Produit::getId))
                                                .toList();
            %>
            <h2><%= magasin.getNom() %> (Id : <%= magasin.getId() %>)</h2>
            <p>Adresse : <%= magasin.getRue()+ ", " + magasin.getCodePostal() + " - " + magasin.getVille()%></p>
            <p>Téléphone : <%= magasin.getNumeroTel() %></p>
            <p>Superficie : <%= magasin.getSuperficie() %> m²</p>
            <p>Produits disponibles : </p>
            <ul>
            <% for(Produit p : produits) { %>
                    <li><%= p.getId() + " | " + p.getNom() + " | " + p.getMarque() + " | " + p.getPrix() + " EUR" %></li>
            <% } %>
            </ul>
            <div class="options">
                <a class="btn" href="<%= request.getContextPath() %>/magasin/addProduit?id=<%= magasin.getId() %>">Ajouter un produit</a>
                <a class="btn" href="<%= request.getContextPath() %>/magasin/deleteProduit?id=<%= magasin.getId() %>">Supprimer un produit</a>
            </div>
<%@include file="/WEB-INF/jsp/foot.jsp" %>