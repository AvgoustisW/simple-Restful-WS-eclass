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

package com.desktopapp;

import java.net.*;
import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Login_Check {
    public JSONObject logIn(String username, String password) throws MalformedURLException, IOException, ParseException{
        String recv;
        String recvbuff = "";
            
        JSONObject json_in = new JSONObject();
        json_in.put("username",username); 
        json_in.put("password",password);
        json_in.put("from","desktop"); 
            
        //dimourgoume antikeimeno url me to link pou tha paei sto webserver gia na kanei to request
        URL loginService = new URL("http://localhost:8080/Services/webresources/LoginService?json="+json_in.toJSONString());
        URLConnection urlcon = loginService.openConnection();
        
        try (BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "UTF8"))) {
            while ((recv = buffread.readLine()) != null)
                recvbuff += recv; //vazoume ta dedomena sto recvbuff
            }
            
            //kanoume parse to String se json kai to epistrefoume sto Menu
            JSONParser parser = new JSONParser();
            JSONObject json_out = (JSONObject)parser.parse(recvbuff);        
            
            return json_out; 
    }  
}




