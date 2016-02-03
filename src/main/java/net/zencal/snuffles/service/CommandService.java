package net.zencal.snuffles.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import net.zencal.snuffles.command.DubBotCommand;
import net.zencal.snuffles.domain.dubtrack.DubChatPayload;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class CommandService implements BeanPostProcessor {
    @Autowired
    protected DubtrackService dubtrackService;
    
    @Autowired
    protected DubBotService dubBotService;
    
    @Autowired
    protected StatsService statsService;
    
    @Autowired
    protected ResourceLoader resourceLoader;

    private Logger logger = LogManager.getLogger(CommandService.class);
    private Logger chatLogger = LogManager.getLogger("chat");

    private List<DubBotCommand> commands = new ArrayList<>();

    public void handleCommand(JSONObject json) {
        JSONObject user = json.getJSONObject("user");
        String chatMessage = json.getString("message");
        Timestamp timestamp = new Timestamp(json.getLong("time"));
        String username = user.getString("username");
        dubBotService.sendDubtrackMessage(username, chatMessage);
        chatLogger.trace(username + ": " + chatMessage);

        DubChatPayload chatPayload = new Gson().fromJson(json.toString(), DubChatPayload.class);
        
        String[] arguments = chatPayload.getMessage().split(" ");
        String trigger = arguments[0].substring(1).toLowerCase(); // strip the leading ! from the command 
        
        for (DubBotCommand command : commands) {
        	if (command.getCommandTriggers().contains(trigger)) {
        		logger.info("Activating command " + command.getClass().getName() + " via trigger " + trigger);
        		command.execute(chatPayload);
        	}
        }
        
        // TODO: migrate these to DubBotCommands
        if(StringUtils.equalsIgnoreCase(chatMessage, "!meme")) {
            sendRandomResponseFromFile("memes.json", username);
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!bemygf")) {
            sendRandomResponseFromFile("bemygf.json", username);
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!hootup") && (StringUtils.equalsIgnoreCase(username, "Welp") || StringUtils.equalsIgnoreCase(username, "mrhoot"))) {
            dubtrackService.hootUp();
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!shupoon") && (StringUtils.equalsIgnoreCase(username, "Welp") || StringUtils.equalsIgnoreCase(username, "poondonkus"))) {
            dubtrackService.shupoon();
        } else if(StringUtils.startsWithIgnoreCase(chatMessage, "!stats")) {
            statsService.sendStatsToDubtrack(chatMessage, username);
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
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        StringBuilder arrayBuilder = new StringBuilder();

        try {
            InputStream memeFile = resource.getInputStream();
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

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// Nothing to do
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		logger.info("postProcessAfterInitialization beginning for bean of class " + bean.getClass().getSimpleName());
		
		if (DubBotCommand.class.isAssignableFrom(bean.getClass())) {
			logger.info("Bean " + bean.getClass().getSimpleName() + " is a DubBotCommand, storing it");
			
			DubBotCommand command = DubBotCommand.class.cast(bean);
			commands.add(command);
		}
		
		return bean;
	}
}
