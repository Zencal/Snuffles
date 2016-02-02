package net.zencal.snuffles.service;

import net.zencal.snuffles.domain.DubBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InitialisationService {
    @Autowired
    protected DubBot dubBot;
    @Autowired
    protected DubBotService dubBotService;
    @Autowired
    protected DubNubService dubNubService;
    @Autowired
    protected DubtrackService dubtrackService;

    @Value(("${irc.enabled}"))
    protected Boolean ircEnabled;
    @Value("${irc.username}")
    protected String ircUsername;
    @Value(("${irc.server}"))
    protected String ircServer;
    @Value(("${irc.channel}"))
    protected String ircChannel;

    @Value(("${pubnub.publish.key}"))
    protected String pubnubPublishKey;
    @Value(("${pubnub.subscribe.key}"))
    protected String pubNubSubscribeKey;

    @Value(("${dubtrack.room}"))
    protected String dubtrackRoom;

    public void init(String[] args) throws InterruptedException {
        if(ircEnabled) {
            dubBot.init(ircUsername);
            dubBotService.init(dubBot);
            dubBotService.connect(ircServer, ircChannel);
        }

        dubNubService.initialize(pubnubPublishKey, pubNubSubscribeKey, "dubtrackfm-".concat(dubtrackRoom));
        dubNubService.subscribe();

        dubtrackService.init();
    }
}
