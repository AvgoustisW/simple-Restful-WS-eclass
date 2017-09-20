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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("UpdateVathmoiService")
public class UpdateVathmoiService {  
    
    @Context
    private UriInfo context;

    public UpdateVathmoiService() {}

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
        JSONParser parser = new JSONParser();
        JSONObject json_in = (JSONObject) parser.parse(json);
        JSONObject json_out = new JSONObject();
        JSONArray errors = new JSONArray();
        
        String vathmos;
        

        try{
            //Connect to DB
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            //elegxoume arxika an o vathmos yparxei idi gia na kseroume an tha kanoume
            //insert stin database, i update
            String sql;
            sql = "SELECT ID,AM,Vathmos FROM vathmoi WHERE ID='"+json_in.get("ID")+"' AND AM='"+json_in.get("AM")+"'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){ //yparxei o vathmos, tha kanoume update
                vathmos = rs.getString("Vathmos");
                sql = "UPDATE vathmoi SET Vathmos='"+json_in.get("vathmos")+"' WHERE ID='"+json_in.get("ID")+"' AND AM='"+json_in.get("AM")+"' AND Vathmos='"+vathmos+"'";
                stmt.executeUpdate(sql);
                json_out.put("Success","0"); //tha epistrepsoume success
            }
            else { //den yparxei o vathmos, prin kanoume insert, prepei na doume an exoun dwthei swsta ta AM/ID
                
                //elegxos gia to AM              
                sql = "SELECT AM FROM foitites WHERE AM='"+json_in.get("AM")+"'"; 
                rs = stmt.executeQuery(sql);
                if(!rs.next()) { //an den vrethike to AM, tote tha epistrepsoume error
                    errors.add("11");
                }
                else { //alliws to error tha parei null
                    errors.add("null");
                }
                
                //elegxos an yparxei o kwdikos mathimatos
                sql = "SELECT ID FROM mathimata WHERE ID='"+json_in.get("ID")+"'"; 
                rs = stmt.executeQuery(sql);
                if(!rs.next()) { //an den vrethike to ID, tote tha epistrepsoume error
                    errors.add("12");
                }
                else { //alliws to error tha parei null
                    errors.add("null");
                }
                
                //an ola pigan kala, kai ta dedomena mas einai egkyra, kanoume insert ton kainourio vathmo
                if(errors.get(0).equals("null") && errors.get(1).equals("null")){ 
                    sql = "INSERT INTO Vathmoi VALUES('"+json_in.get("ID")+"','"+json_in.get("AM")+"','"+json_in.get("vathmos")+"')";
                    stmt.execute(sql);  
                    json_out.put("Success","0"); //epistrefoume success
                } 
                else { //alliws ypirksan errors kai prepei na valoume sto json pou tha epistrepsoume
                    json_out.put("Error",errors);
                }   
            }         
            
        } //end try
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
