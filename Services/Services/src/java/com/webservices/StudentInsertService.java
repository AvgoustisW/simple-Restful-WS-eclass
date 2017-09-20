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

import java.nio.charset.Charset;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import org.json.simple.JSONObject;
import javax.ws.rs.QueryParam;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@Path("StudentInsertService")
public class StudentInsertService {
    @Context
    private UriInfo context;


    public StudentInsertService() {
    }


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
        JSONParser parser  = new JSONParser();
        JSONObject json_in = (JSONObject) parser.parse(json);
        // DOESN'T PARSE UTF8 CORRECTLY THUS RANDOM CHARACTERS -- UKNOWN FIX THUS FAR
        JSONObject json_out = new JSONObject();
        try{
            //Connect to DB
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            //elegxoume an yparxei idi o foititis pou theloume na eisagoume
            String sql;
            sql = "SELECT AM FROM foitites WHERE AM="+json_in.get("AM");
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){ //an yparxei o foititis, return error
                json_out.put("Error","10");
            }
            else { //alliws insert ton foititi
                sql = "INSERT INTO foitites VALUES('"+json_in.get("AM")+"','"+json_in.get("FirstName")+"','"+json_in.get("LastName")+"','"+json_in.get("Semester")+"')";
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
