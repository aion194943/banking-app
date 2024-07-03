package com.example.proiect_android_ionanamaria;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

public class Network {
    public static List<Card>  JSON_load() throws IOException, JSONException{
        ArrayList<Card> listCards=new ArrayList<>();
        String link="https://pastebin.com/raw/UnX1aDbT";
        InputStream inputStream= new URL(link).openConnection().getInputStream();
        Card Card= null;
        String JSONobj= new Scanner(inputStream).useDelimiter("\\A").next();

        JSONArray JACards= new JSONArray(JSONobj);
        for(int i=0; i< JACards.length(); i++){
            Card= new Card();

            JSONObject User= JACards.getJSONObject(i);
            JSONObject JOCard=User.getJSONObject("Card");
            JSONObject JODetails=JOCard.getJSONObject("Details");



            Card.setBankName(JOCard.getString("Bankname"));
            Card.setCurrency(JOCard.getString("Currency"));


            Card.setCardNum(JODetails.getLong("CardNumber"));
            Card.setCardCVV(JODetails.getInt("CVV"));
            Card.setExpMth(JODetails.getInt("ExpMth"));
            Card.setExpYr(JODetails.getInt("ExpYear"));
            Card.setBalance(JODetails.getInt("Balance"));

            listCards.add(Card);

        }
        return listCards;
    }

}

