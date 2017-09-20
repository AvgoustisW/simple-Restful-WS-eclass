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

<%@page import="java.util.StringTokenizer"%>
<%@page import="java.sql.*"%>
<%@page import="java.net.*" %>
<%@page import="java.io.*" %>
<%@page import="org.json.simple.parser.JSONParser" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login check page</title>
    </head>
    <body>
        <%
            //pernoume tis parametrous pou exoume thesi sto session
            final String username = request.getParameter("username"); 
            final String password = request.getParameter("password");
                
            JSONObject json_out = new JSONObject();
            
            //ftiaxnoume to json object mas gia na steiloume sto webservice
            json_out.put("username",username);
            json_out.put("password",password);
            json_out.put("from","web");
            
            String recv;
            String recvbuff = "";
            
            //dimourgoume antikeimeno url me to link pou tha paei sto webserver gia na kanei to request
            URL jsonpage = new URL("http://localhost:8080/Services/webresources/LoginService?json="+json_out.toJSONString());
            URLConnection urlcon = jsonpage.openConnection();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "UTF8"));

            while ((recv = buffread.readLine()) != null)
                recvbuff += recv; //vazoume ta dedomena sto recvbuff
            buffread.close();
            
            //kanoume parse to String se json kai to epistrefoume sto Menu
            JSONParser parser = new JSONParser();
            JSONObject json_in = (JSONObject)parser.parse(recvbuff);
            
            
            if (json_in.get("Error") != null){ //fail to login
                switch (Integer.parseInt(json_in.get("Error").toString())) { //switch error code
                    case 1: 
                        %><h2>Wrong username or password.<br></h2><%
                        break;
                    case 5:
                        %><h2>Γραμματεία ή admin πρέπει να συνδεθούν από desktop app.<br></h2><%
                        break;
                    case 100:    
                        %><h2>Unexpected error.<br></h2><%
                        break;
                    default:
                        %><h2>Unexpected error.<br></h2><%        
                }
                %><h2>Press <a href="login.html">here</a> to try again...</h2><%
            }
            else { //success login
                session.setAttribute("AM", json_in.get("AM"));
                session.setAttribute("FirstName", json_in.get("FirstName"));
                session.setAttribute("LastName", json_in.get("LastName"));
        %>
                <h2>Welcome <%= json_in.get("FirstName")%> <%= json_in.get("LastName")%> <br> 
                You may proceed to the home page <a href="home.jsp">here.</a></h2>
        <%
            }
        %>
        
    </body>
</html>
