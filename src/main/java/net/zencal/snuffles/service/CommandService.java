package net.zencal.snuffles.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.Scanner;

@Service
public class CommandService {
    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected DubBotService dubBotService;

    private Logger logger = LogManager.getLogger(CommandService.class);
    private Logger chatLogger = LogManager.getLogger("chat");


    public void handleCommand(JSONObject json) {
        JSONObject user = json.getJSONObject("user");
        String chatMessage = json.getString("message");
        Timestamp timestamp = new Timestamp(json.getLong("time"));
        String username = user.getString("username");
        dubBotService.sendDubtrackMessage(username, chatMessage);
        chatLogger.trace(username + ": " + chatMessage);

        if(StringUtils.equalsIgnoreCase(chatMessage, "!meme")) {
            sendRandomResponseFromFile("memes.json", username);
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!btfo")) {
            dubtrackService.sendBTFO();
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!bemygf")) {
            sendRandomResponseFromFile("bemygf.json", username);
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!hootup") && (StringUtils.equalsIgnoreCase(username, "Welp") || StringUtils.equalsIgnoreCase(username, "mrhoot"))) {
            dubtrackService.hootUp();
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!shupoon") && (StringUtils.equalsIgnoreCase(username, "Welp") || StringUtils.equalsIgnoreCase(username, "poondonkus"))) {
            dubtrackService.shupoon();
        }
    }

    private void sendRandomResponseFromFile(String filename, String username) {
        JSONArray responses = retrieveJSONArrayFromFile(filename);
        Random random = new Random();
        Integer randomInt = random.nextInt((responses.length()) + 1);
        String response = (String) responses.get(randomInt);
        dubtrackService.sendMessage(response.replaceAll("<<username>>", username));
    }

    public JSONArray retrieveJSONArrayFromFile(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();
        File memeFile = new File(classLoader.getResource(filename).getFile());
        StringBuilder arrayBuilder = new StringBuilder();

        try {
            Scanner scanner = new Scanner(memeFile);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                arrayBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.debug(e);
        }

        return new JSONArray(arrayBuilder.toString());
    }
}
