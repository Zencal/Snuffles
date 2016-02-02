package net.zencal.snuffles.service;

import net.zencal.snuffles.domain.DubBot;
import org.jibble.pircbot.IrcException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DubBotService {
    protected Logger logger = LogManager.getLogger(DubBotService.class);

    @Autowired
    protected DubNubService dubNubService;
    @Autowired
    protected DubtrackService dubtrackService;

    @Value("${irc.enabled}")
    protected Boolean enabled;
    @Value("${irc.password}")
    protected String password;

    protected DubBot dubBot;
    protected String channel;

    public void init(DubBot dubBot) {
        this.dubBot = dubBot;
    }

    public void connect(String hostname, String channel) {
        connect(hostname, null, channel, null);
    }

    public void connect(String hostname, Integer port, String channel, String key) {
        this.channel = channel;
        try {
            if(port != null) {
                dubBot.connect(hostname, port);
            } else {
                dubBot.connect(hostname);
            }

            dubBot.sendMessage("NickServ", "IDENTIFY ".concat(password));
        } catch (IrcException|IOException e) {
            log(e.getMessage());
        }
        if(key != null) {
            dubBot.joinChannel(this.channel);
        } else {
            dubBot.joinChannel(this.channel, key);
        }
    }

    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        ArrayList<String> params = new ArrayList<>(Arrays.asList(message.split(" ")));
        String command = params.get(0);
        params.remove(0);

        switch(command) {
            case "!say":
                StringBuilder msg = new StringBuilder();
                msg.append(sender).append(": ");
                for(String messagePart : params) {
                    msg.append(messagePart).append(" ");
                }
                dubtrackService.sendMessage(msg.toString());
            case "!users":
                dubNubService.hereNow();
                dubBot.sendMessage(channel, dubtrackService.printUserList());
        }
    }

    public void log(String line) {
        logger.trace(line);
    }

    public void sendDubtrackUserJoin(String username) {
        if(enabled) {
            StringBuilder message = new StringBuilder();
            message.append(username).append(" ");
            message.append("has joined");
            dubBot.sendMessage(channel, message.toString());
        }
    }

    public void sendDubtrackUserLeave(String username) {
        if(enabled) {
            StringBuilder message = new StringBuilder();
            message.append(username).append(" ");
            message.append("has left");
            dubBot.sendMessage(channel, message.toString());
        }
    }

    public void sendDubtrackMessage(String username, String chatMessage) {
        if(enabled) {
            StringBuilder message = new StringBuilder();
//        message.append("[").append(timestamp).append("] ");
            message.append(username).append(": ");
            message.append(chatMessage);
            dubBot.sendMessage(channel, message.toString());
        }
    }
}
