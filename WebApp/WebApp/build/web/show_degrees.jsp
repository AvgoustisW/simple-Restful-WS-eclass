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
<%@page import="org.json.simple.*" %>
<%@page import="org.json.simple.parser.JSONParser" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show degrees</title>
    </head>
    <body>
        <%
            JSONParser parser = new JSONParser();
            JSONObject json_in = new JSONObject();
            json_in.put("AM",session.getAttribute("AM"));
            
            String recv;
            String recvbuff = "";
            
            //dimourgoume antikeimeno url me to link pou tha paei sto webserver gia na kanei to request
            URL jsonpage = new URL("http://localhost:8080/Services/webresources/ShowInfoService?json="+json_in.toJSONString());
            URLConnection urlcon = jsonpage.openConnection();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "UTF8"));

            while ((recv = buffread.readLine()) != null)
                recvbuff += recv; //vazoume ta dedomena sto recvbuff
            buffread.close();
            
            //kanoume parse to String se json kai to epistrefoume sto Menu
            JSONObject json_out = (JSONObject) parser.parse(recvbuff);
            JSONArray Mathimata = (JSONArray)json_out.get("mathima");
            JSONArray ID = (JSONArray)json_out.get("ID");
            JSONArray Vathmoi = (JSONArray)json_out.get("vathmos");
            
            %><h3><u>ID / Μάθημα / Βαθμός</u></h3><%
            for(int i=0; i<Mathimata.size(); i++) {
                %><%= ID.get(i)%> / <%= Mathimata.get(i)%> / <%= Vathmoi.get(i)%><br><%
            }
        %>
        
        <br><a href="home.jsp">Back to menu</a><br><br>
        <a href="login.html">Log out</a>

        
    </body>
    
    
</html>
