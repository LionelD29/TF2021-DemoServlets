<%@include file="../WEB-INF/jsp/head.jsp" %>
<% increaseA(); %>

<h1>Les directives JSP</h1>

<h2>La directive d'expression</h2>

<p>valeur de l'expression 1+1 = <%= 1+1 %></p>

<h2>La directive de scriptlet</h2>

<p>okok</p>

<% for (int i = 0; i < 5; i++) { %>
    <p> salut - <%= a + i %> </p>
<% } %>

<h2>Les directives de déclaration</h2>

<%!
    int a = 5;

    private void increaseA() {
        a = 10;
    }
%>

<h2>Les tags de directives</h2>

<h3>page</h3>
<%@ page import="models.Produit" %>
<%@ page buffer="8kb" autoFlush="true" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- <%@ page extends="javax.servlet.http.HttpServlet" %> --%>
<%-- <%@ page errorPage="error.jsp" %> --%>

<h3>include</h3>
<p>Prend le contenu d'un fichier pour l'inclure dans le fichier actuel</p>

<h2>Les données accessibles</h2>



<%@include file="../WEB-INF/jsp/foot.jsp" %>

