<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <title>MonApp</title>
</head>
<body>
    <div class="container">
        <header>
             <h1>MonApp</h1>
             <nav>
                 <a href="<%= request.getContextPath() %>">Accueil</a>
                 <a href="<%= request.getContextPath() %>/produit">Produits</a>
                 <a class="active" href="<%= request.getContextPath() %>/personne">Personnes</a>
                 <a href="<%= request.getContextPath() %>/magasin">Magasins</a>
             </nav>
         </header>
        <hr>