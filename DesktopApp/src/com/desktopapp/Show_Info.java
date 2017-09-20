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

import java.io.*;
import java.net.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Show_Info {
    
    
    public JSONObject show(String am) throws MalformedURLException, IOException, ParseException{
        String recv;
        String recvbuff = "";
        
        JSONObject json_in = new JSONObject();
        
        json_in.put("AM", am);
        
        //dimourgoume antikeimeno url me to link pou tha paei sto webserver gia na kanei to request
        URL showInfoService = new URL("http://localhost:8080/Services/webresources/ShowInfoService?json="+json_in.toJSONString());
        URLConnection urlcon = showInfoService.openConnection();
        BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream(), "UTF8"));

        while ((recv = buffread.readLine()) != null)
            recvbuff += recv; //vazoume ta dedomena sto recvbuff
        buffread.close();

        //kanoume parse to String se json kai to epistrefoume sto Menu
        JSONParser parser = new JSONParser();
        JSONObject json_out = (JSONObject) parser.parse(recvbuff);

        return json_out;
    }

}
        

