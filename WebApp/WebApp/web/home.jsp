<!-- ============================================== -->
<!--  TEI Athinas - Tmima Mixanikwn Pliroforikis    -->
<!--                                                -->
<!--          Teliki Ergasia sto mathima            -->
<!--           Diktyakoy Programmatismou            -->
<!--                                                -->
<!--                                                -->
<!--      Avgoustis Aristeidis, 101049              -->
<!--      Christou Konstantinos, 101040             -->
<!-- ============================================== -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.net.*" %>
<%@page import="java.io.*" %>
<%@page import="javax.swing.JButton" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home page</title>
    </head>
    <body>
        <h2>Welcome to your home page <%= session.getAttribute("FirstName")%> <%= session.getAttribute("LastName")%>.</h2>   
        
        <!-- Option menu -->
        <form name="Input" method="post" action="show_degrees.jsp">
            <input type="submit" value="Show my degrees">
        </form>
        
        <br><a href="login.html">Log out</a>
    </body>
</html>
