package net.zencal.snuffles.domain;

import net.zencal.snuffles.service.DubBotService;
import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DubBot extends PircBot {
    @Autowired
    protected DubBotService dubBotService;

    @Value("${irc.username}")
    protected String ircUsername;

    @PostConstruct
    public void init() {
        setName(ircUsername);
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        dubBotService.onMessage(channel, sender, login, hostname, message);
    }

    @Override
    public void log(String line) {
        dubBotService.log(line);
    }
}
