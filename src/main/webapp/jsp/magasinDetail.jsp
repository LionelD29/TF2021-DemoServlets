<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="models.Magasin" %>
<%@ page import="models.Produit" %>
<%@ page import="static utils.Util.escapeSpecialCharacters" %>

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
            <% Magasin magasin = (Magasin) request.getAttribute("magasin"); %>
            <h2><%= escapeSpecialCharacters(magasin.getNom()) %></h2>
            <p>ID : <%= magasin.getId() %></p>
            <p>Adresse : <%= magasin.getRue()+ ", " + magasin.getCodePostal() + " - " + magasin.getVille()%></p>
            <p>Téléphone : <%= magasin.getNumeroTel() %></p>
            <p>Superficie : <%= magasin.getSuperficie() %> m²</p>
            <p>Produits disponibles : </p>
            <ul>
            <% for(Produit p : magasin.getProduitsDispo()) { %>
                    <li><%= p.getNom() + " | " + p.getMarque() + " | " + p.getPrix() + " EUR" %></li>
            <% } %>
            </ul>
<%@include file="/WEB-INF/jsp/foot.jsp" %>