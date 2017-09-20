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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UpdateVathmoi {
    
    public JSONObject uVathmoi(String ID, String AM, String vathmos) throws MalformedURLException, IOException, ParseException{
        JSONObject json_in = new JSONObject();
        json_in.put("ID",ID);
        json_in.put("AM",AM);
        json_in.put("vathmos",vathmos);
        
        String recv;
        String recvbuff = "";
        
        //dimourgoume antikeimeno url me to link pou tha paei sto webserver gia na kanei to request
        URL updateVathmoiService = new URL("http://localhost:8080/Services/webresources/UpdateVathmoiService?json="+json_in.toJSONString());
        URLConnection urlcon = updateVathmoiService.openConnection();
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
