// ----------------------------------------------
//  TEI Athinas - Tmima Mixanikwn Pliroforikis
//
//          Teliki Ergasia sto mathima
//           Diktyakoy Programmatismou
//
//
//      Avgoustis Aristeidis, 101049
//      Christou Konstantinos, 101040
// ----------------------------------------------

package com.webservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import org.json.simple.*;
import javax.ws.rs.QueryParam;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("LoginService")
public class LoginService {
    String role;
    
    @Context
    private UriInfo context;

    //default constructor
    public LoginService() {}

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("json") String json) throws ParseException {
        //info to connect with database
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/teliki";
        final String USER = "root";
        final String PASS = "";
        Connection conn;
        Statement stmt;
        
        //parse ta info se json gia na ta diaxeiristoume
        JSONObject json_out = new JSONObject();
        JSONParser parser  = new JSONParser();
        JSONObject json_in = (JSONObject) parser.parse(json);
        
        try{
            //connect with database
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            String sql,sql2; //string to input query to run.
                       
            //Need to read the Role here to see who's acessing
            ResultSet rs;
            sql= "SELECT Username,Role FROM users WHERE Username='"+json_in.get("username")+"' AND Password='"+json_in.get("password")+"'";
            rs = stmt.executeQuery(sql);          
            
            
            if(rs.next()) { //if username,password match found
                role  = rs.getString("Role");
                if (json_in.get("from").equals("web")) { //if connected from web app
                    switch (role) {
                        case "1": case "2": //admin/grammateia prospathoun na kanoun login apo web app
                            json_out.put("Error","5"); //error oti admin kai grammateia prepei na kanoun login apo desktop app
                            break;
                        case "3": //this will give access
                            json_out.put("AM",rs.getString("Username").substring(2));
                            sql2 = "SELECT FirstName,LastName FROM foitites WHERE AM='"+json_out.get("AM")+"'";
                            rs = stmt.executeQuery(sql2);
                            rs.next();
                            json_out.put("FirstName",rs.getString("FirstName"));
                            json_out.put("LastName",rs.getString("LastName"));
                            break;
                        default:
                            json_out.put("Error","100"); //general error code
                    }
                }
                else if(json_in.get("from").equals("desktop")) { //if connected from desktop app
                    switch (role) {
                        case "3": //foititis prospathei na kanei login apo desktop app
                            json_out.put("Error","4");  //error oti o foititis prepei na kanei login apo desktop app                   
                            break;
                        case "1": case "2": //this will give access
                            json_out.put("Role", role);
                            break;
                        default:
                            json_out.put("Error","100"); //general error code
                    }
                }
            }//end if(rs.next())
            else { //invalid username or password
                json_out.put("Error","1"); //error code for wrong username/password
            }  
        }//end try//end try
        catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }
        catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        
        return json_out.toJSONString();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
