package net.zencal.snuffles.service;

import net.zencal.snuffles.domain.DubBot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jibble.pircbot.IrcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
    @Value(("${irc.server}"))
    protected String hostname;
    @Value(("${irc.channel}"))
    protected String channel;
    @Value(("${irc.port}"))
    protected Integer port;
    @Value(("${irc.key}"))
    protected String key;

    @Autowired
    protected DubBot dubBot;

    @PostConstruct
    public void connect() {
        if(enabled) {
            try {
                if (port != null) {
                    dubBot.connect(hostname, port);
                } else {
                    dubBot.connect(hostname);
                }

                dubBot.sendMessage("NickServ", "IDENTIFY ".concat(password));
            } catch (IrcException | IOException e) {
                log(e.getMessage());
            }
            if (key != null) {
                dubBot.joinChannel(channel);
            } else {
                dubBot.joinChannel(channel, key);
            }
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
            message.append(username).append(": ");
            message.append(chatMessage);
            dubBot.sendMessage(channel, message.toString());
        }
    }
}
