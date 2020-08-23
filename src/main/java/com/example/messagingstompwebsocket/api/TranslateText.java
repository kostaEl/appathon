package com.example.messagingstompwebsocket.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TranslateText {

    OkHttpClient client = new OkHttpClient();

    public String run(String text, String inputlan, String outputlan) throws IOException {

        Request request = new Request.Builder()
                .url("https://translated-mymemory---translation-memory.p.rapidapi.com/api/get?mt=1&onlyprivate=0&de=a%40b.c&langpair="+inputlan+"%7C"+outputlan+"&q="+text)
                .get()
                .addHeader("x-rapidapi-host", "translated-mymemory---translation-memory.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "719aa7f967msh8885dbeae712cc0p141f2fjsn86e293c1ad24")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String answer=response.body().string();
            String jsonString = answer; //assign your JSON String here
            JSONObject obj = new JSONObject(jsonString);
            String translated = obj.getJSONObject("responseData").getString("translatedText");
            return translated;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return text;
    }

    public static void main(String[] args) throws IOException {
        TranslateText example = new TranslateText();
        String response = example.run("good morning","en","it");
        System.out.println(response);
    }
}