<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="be.technifutur.demoServlets.models.Personne" %>
<%@ page import="be.technifutur.demoServlets.services.PersonneService" %>
<%@ page import="be.technifutur.demoServlets.services.PersonneServiceImpl" %>
<%@ page import="static be.technifutur.demoServlets.utils.Util.escapeSpecialCharacters" %>

<%@include file="/WEB-INF/jsp/head.jsp" %>
        <h2>Liste des personnes</h2>
        <main>
            <ul>
            <%
                PersonneService service = PersonneServiceImpl.getInstance();
                List<Personne> list = service.getAll();
            %>

            <% for (Personne p : list) { %>
                <li><%= escapeSpecialCharacters(p.getPrenom()) + " " + escapeSpecialCharacters(p.getNom()) %></li><%= "\n" %>
            <% } %>  
            </ul>
            <form action="<%= request.getContextPath() %>/personne" method="post">
                <input type="text"
                        placeholder="Prenom"
                        maxlength="30"
                        name="firstname"
                        required
                        autofocus>
                <input type="text"
                        placeholder="Nom"
                        maxlength="30"
                        name="lastname"
                        required>
                <input class="btn" type="submit" value="Ajouter">
            </form>
        </main>
<%@include file="/WEB-INF/jsp/foot.jsp" %>