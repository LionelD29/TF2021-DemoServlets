<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <form action="<%= request.getContextPath() %>/magasin/addMagasin" method="post">
                <input type="text" name="name" placeholder="Nom" required autofocus>
                <input type="text" name="street" placeholder="Rue" required>
                <input type="text" name="city" placeholder="Ville" required>
                <input type="text" name="postalCode" placeholder="Code postal" required>
                <input type="number" name="number" placeholder="Numéro dans la rue" required>
                <input type="number" name="area" placeholder="Superficie" required>
                <input class="btn" type="submit" value="Ajouter">
            </form>
<%@include file="/WEB-INF/jsp/foot.jsp" %>