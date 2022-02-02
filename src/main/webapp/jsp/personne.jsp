<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Personne" %>
<%@ page import="services.PersonneService" %>
<%@ page import="services.PersonneServiceImpl" %>
<%@ page import="static utils.Util.escapeSpecialCharacters" %>

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