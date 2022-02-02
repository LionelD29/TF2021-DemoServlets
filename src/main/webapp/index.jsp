<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style.css">
    <title>Accueil</title>
</head>
<body>
    <div class="container">
        <header>
            <h1>MonApp</h1>
            <nav>
                <a class="active" href="<%= request.getContextPath() %>">Accueil</a>
                <a href="<%= request.getContextPath() %>/produit">Produits</a>
                <a href="<%= request.getContextPath() %>/personne">Personnes</a>
                <a href="<%= request.getContextPath() %>/magasin">Magasins</a>
            </nav>
        </header>
        <hr>
        <h2>Bienvenue sur la page d'accueil !</h2>
    </div>
</body>
</html>