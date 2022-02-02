<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.List" %>
<%@ page import="models.Magasin" %>
<%@ page import="services.MagasinService" %>
<%@ page import="dataAccess.MagasinDAO" %>
<%@ page import="java.util.Comparator" %>
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
                <a href="<%= request.getContextPath() %>">Accueil</a>
                <a href="<%= request.getContextPath() %>/produit">Produits</a>
                <a href="<%= request.getContextPath() %>/personne">Personnes</a>
                <a class="active" href="<%= request.getContextPath() %>/magasin">Magasins</a>
            </nav>
        </header>
        <hr>
        <h2>Liste des magasins</h2>
        <ul>

        <% List<Magasin> list = (List<Magasin>) request.getAttribute("list"); %>

        <% for(Magasin magasin : list) { %>
                <li>
                    <a href="<%= request.getContextPath() %>/magasin/detail?id=<%= magasin.getId() %>"><%= magasin.getNom() %></a>
                </li>
        <% } %>

        </ul>
        <div class="options">
            <a class="btn" href="<%= request.getContextPath() %>/magasin/addMagasin">Ajouter un magasin</a>
        </div>
<%@include file="/WEB-INF/jsp/foot.jsp" %>