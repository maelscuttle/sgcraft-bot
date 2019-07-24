package com.gmail.maelgrove.SgcraftTlg.Telegram;

import com.gmail.maelgrove.SgcraftTlg.Telegram.Model.Message;
import com.gmail.maelgrove.SgcraftTlg.Utils.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Telegram {
    private final String API_URL_GETME = "https://api.telegram.org/bot%s/getMe";
    private final String API_URL_GENERAL = "https://api.telegram.org/bot%s/%s";
    public JsonObject authJson;
    public boolean connected = false;
    public String token;

    public boolean auth(String token) {
        this.token = token;
        return reconnect();
    }

    public boolean reconnect() {
        try {
            JsonObject obj = sendGet(String.format(API_URL_GETME, token));
            authJson = obj;
            System.out.print("[Telegram] Established a connection with the telegram servers.");
            connected = true;
            return true;
        } catch (Exception e) {
            connected = false;
            System.out.print("[Telegram] Sorry, but could not connect to Telegram servers. The token could be wrong.");
            return false;
        }
    }

    public void sendMsg(String msg) {
        sendMsg(Config.Default.getChatId(), msg);
    }

    public void sendMsg(Long id, String msg) {
        Message chat = new Message();
        chat.chat_id = id;
        chat.text = msg;
        sendMsg(chat);
    }

    public void sendMsg(Message chat) {
        Gson gson = new Gson();
        post("sendMessage", gson.toJson(chat, Message.class));
    }


    public void post(String method, String json) {
        try {
            String body = json;
            URL url = new URL(String.format(API_URL_GENERAL, Config.Default.getBotToken(), method));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json; ; Charset=UTF-8");
            connection.setRequestProperty("Content-Length", String.valueOf(body.length()));

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, "UTF-8"));
            writer.write(body);
            writer.close();
            wr.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            writer.close();
            reader.close();
        } catch (Exception e) {
            reconnect();
            System.out.print("[Telegram] Disconnected from Telegram, reconnect...");
        }

    }

    public JsonObject sendGet(String url) throws IOException {
        String a = url;
        URL url2 = new URL(a);
        URLConnection conn = url2.openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String all = "";
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            all += inputLine;
        }

        br.close();
        JsonParser parser = new JsonParser();
        return parser.parse(all).getAsJsonObject();

    }

}
