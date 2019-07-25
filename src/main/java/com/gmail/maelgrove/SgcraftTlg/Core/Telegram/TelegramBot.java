package com.gmail.maelgrove.SgcraftTlg.Core.Telegram;

import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Model.SendMessage;
import com.gmail.maelgrove.SgcraftTlg.Core.Telegram.Model.Update;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a Telegram bot client.
 */
public class TelegramBot {

    private final String API_URL_GETME = "https://api.telegram.org/bot%s/getMe";
    private final String API_URL_GENERAL = "https://api.telegram.org/bot%s/%s";
    private final String API_URL_GETUPDATES = "https://api.telegram.org/bot%s/getUpdates?offset=%d";

    private JsonObject authJson;
    private int lastUpdate;
    private boolean isConnected = false;
    private String token;
    private Gson gson = new Gson();

    private List<UpdateHandler> updateHandlers = new ArrayList<UpdateHandler>();


    /**
     * Attempts to authenticate the bot.
     * @param token The bot token, issued by BotFather
     * @return True if the authentication was successful, otherwise false.
     */
    public boolean tryAuthenticate(String token) {
        this.token = token;
        return tryReconnect();
    }

    /**
     * @return True if the bot is connected, otherwise false.
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return The current token.
     */
    public String getToken() {
        return token;
    }

    /**
     * @return True if the bot has a token set, otherwise false.
     */
    public boolean hasToken() {
        return token != null && !token.isEmpty();
    }

    /**
     * @return True if the bot is authenticated, otherwise false.
     */
    public boolean isAuthenticated() {
        return authJson != null;
    }

    public boolean isReady() {
        return isConnected && authJson != null;
    }

    public void addUpdateHandler(UpdateHandler handler) {
        updateHandlers.add(handler);
    }

    /**
     * Attempts to reconnect the bot.
     * @return True if the re-conncetion was successful, otherwise false.
     */
    public boolean tryReconnect() {
        try {
            JsonObject obj = sendGet(String.format(API_URL_GETME, token));
            authJson = obj;
            isConnected = true;
            return true;
        } catch (Exception e) {
            isConnected = false;
            return false;
        }
    }

    /**
     * Sends a new sendMessage.
     * @param sendMessage The sendMessage to send.
     */
    public void sendMessage(SendMessage sendMessage) {
        post("sendMessage", gson.toJson(sendMessage, SendMessage.class));
    }

    public boolean tryProcessNextUpdate() {

        JsonObject rawUpdate = null;
        try {
            rawUpdate = sendGet(String.format(API_URL_GETUPDATES, token, lastUpdate + 1));
        } catch (IOException e) {
            return false;
        }

        if(rawUpdate == null || !rawUpdate.has("result"))
            return false;

        for(JsonElement element : rawUpdate.getAsJsonArray("result")) {
            if(element.isJsonObject()) {
                Update update = gson.fromJson(element, Update.class);
                if(lastUpdate == update.getUpdateId())
                    return true;
                lastUpdate = update.getUpdateId();

                if(update.getMessage() == null)
                    return true;

                UpdateHandlerContext context = new UpdateHandlerContext(this, update);
                for(UpdateHandler handler : updateHandlers) {
                    handler.handleUpdate(context);
                }
            }
        }

        return true;
    }

    private void post(String method, String json) {
        try {
            String body = json;
            URL url = new URL(String.format(API_URL_GENERAL, token, method));
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
            Bukkit.getLogger().info(e.getMessage());
            tryReconnect();
        }

    }

    private JsonObject sendGet(String url) throws IOException {
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
