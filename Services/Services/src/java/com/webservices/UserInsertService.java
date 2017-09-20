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
import javax.ws.rs.QueryParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("UserInsertService")
public class UserInsertService {

    @Context
    private UriInfo context;

    
    public UserInsertService() {}

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("json") String json) throws ParseException {
        //  Database credentials
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/teliki";
        final String USER = "root";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;
        
        //parse ta info pou irthan se json
        JSONObject json_out = new JSONObject();
        JSONParser parser  = new JSONParser();
        JSONObject json_in = (JSONObject) parser.parse(json);

        try{
            //Connect to DB
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            //elegxoume an yparxei o user prin kanoume insert
            String sql;
            sql = "SELECT username FROM users WHERE username='"+json_in.get("username")+"'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){ //an yparxei emfanizoume error
                json_out.put("Error","10");
            }
            else { //an den yparxei, kanoume insert ton neo xristi mas
                sql = "INSERT INTO users (Username, Password, Role) VALUES('"+json_in.get("username")+"','"+json_in.get("password")+"','"+json_in.get("role")+"')";
                stmt.execute(sql);
                json_out.put("Success","0");
            }

        }//end try
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
