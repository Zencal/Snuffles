package net.zencal.snuffles.domain;

import net.zencal.snuffles.service.DubBotService;
import org.jibble.pircbot.PircBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DubBot extends PircBot {
    @Autowired
    protected DubBotService dubBotService;

    public void init(String name) {
        setName(name);
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
