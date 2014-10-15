package net.codeoclock.hugh.catsonthego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hugh on 19/07/2014.
 */
public class CatManager {
    String instagramApiURL;
    String catsEndpoint;
    String clientId;
    String tag;
    ArrayList<URL> catsImageURLList;
    int index = 0;
    public CatManager(){
        this.clientId = "96f9bfe647514976ba6cea29b9cb33f2";
        this.tag = "cats";
        this.instagramApiURL = "https://api.instagram.com";
        this.catsEndpoint = "/v1/tags/"+this.tag+"/media/recent";
        this.index = 0;
        try {
            this.loadCats();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    ArrayList cats;
    public void loadCats() throws JSONException {
        String fullURI = this.instagramApiURL;
        fullURI += this.catsEndpoint;
        fullURI += "?client_id="+this.clientId;
        JSONObject catList = getJSONFromURL(fullURI);

        // grab text urls from json into array

        JSONArray catJSONArray = catList.getJSONArray("data");
        for(int x = 0; x < catJSONArray.length(); x++){
            try {
                this.catsImageURLList.add(new URL(catJSONArray.getJSONObject(x).getJSONObject("images").getJSONObject("standard_resolution").getString("url")));
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        Log.i("Cats","Loaded");
    }

    public URL getNextCat(){
        URL Cat = this.catsImageURLList.get(this.index);
        this.index++;
        this.index = this.index % this.catsImageURLList.size();
        return Cat;
    }

    public Bitmap getNextCatImage(){
        return getBitmapFromURL(this.getNextCat());
    }

    public static JSONObject getJSONFromURL(String src){
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(input));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            JSONObject result = null;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            try {
                result = new JSONObject(inputStr);
            }
            catch(JSONException e){
                e.printStackTrace();
                Log.e("JSONException",e.getMessage());
            }
            Log.e("JSON","returned");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }

    public static Bitmap getBitmapFromURL(URL url) {
        try {
            Log.e("src", url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}
