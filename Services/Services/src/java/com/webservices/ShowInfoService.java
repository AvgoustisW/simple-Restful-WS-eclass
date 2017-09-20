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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javax.ws.rs.QueryParam;
import org.json.simple.parser.ParseException;

@Path("ShowInfoService")
public class ShowInfoService {
 
    @Context
    private UriInfo context;    
    
    //default constructor
    public ShowInfoService() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public String getJson(@QueryParam("json") String json) throws ParseException {
        //info to connect with DB
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost/teliki";
        final String USER = "root";
        final String PASS = "";
        Connection conn;
        Statement stmt;
        
        //parse ta info pou irthan se json
        JSONParser parser = new JSONParser();
        JSONObject json_in = (JSONObject) parser.parse(json);
        JSONObject json_out = new JSONObject();
        
        //"spame" to json se JSONArray
        JSONArray Mathimata = new JSONArray();
        JSONArray ID = new JSONArray();
        JSONArray Vathmoi = new JSONArray();

        boolean found = false;;

        try{
            //create with DB
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            String sql; 
            sql = "SELECT mathimata.ID,mathimata.Mathima,vathmoi.Vathmos FROM vathmoi INNER JOIN mathimata ON mathimata.ID=vathmoi.ID WHERE AM='"+json_in.get("AM")+"'";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){ //while having info  
                //getting info from database into values
                Mathimata.add(rs.getString("Mathima"));
                ID.add(rs.getString("ID"));
                Vathmoi.add(rs.getString("Vathmos"));
                if(!found)
                    found = true;
            }
                            
            if(!found) { //if not found, tha exoume error sto return
                json_out.put("Error","404");
            }
            else { //alliws ftiaxnoume to json pou tha epistrepsoume
                json_out.put("mathima",Mathimata);
                json_out.put("ID",ID);
                json_out.put("vathmos",Vathmoi);
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
