package net.zencal.snuffles.service;

import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;
import net.zencal.snuffles.callback.HereNowCallback;
import net.zencal.snuffles.callback.PresenceCallback;
import net.zencal.snuffles.callback.PublishCallback;
import net.zencal.snuffles.callback.SubscribeCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DubNubService {
    protected Logger logger = LogManager.getLogger(DubNubService.class);

    protected Pubnub pubnub;
    protected String channel;

    @Autowired
    protected DubBotService dubBotService;
    @Autowired
    protected SubscribeCallback subscribeCallback;
    @Autowired
    protected PresenceCallback presenceCallback;
    @Autowired
    protected HereNowCallback hereNowCallback;

    public void initialize(String publishKey, String subscribeKey, String channel) {
        pubnub = new Pubnub(publishKey, subscribeKey);
        this.channel = channel;
        StringBuilder message = new StringBuilder();
        message.append("Initialized pubnub with:\n");
        message.append("publish_key: ").append(publishKey);
        message.append("\nsubscribe_key: ").append(subscribeKey);
        message.append("\nchannel: ").append(channel);
        logger.debug(message.toString());
    }

    public void subscribe() {
        try {
            pubnub.subscribe(channel, subscribeCallback);
        } catch (PubnubException e) {
            logger.debug(e.toString());
        }
    }

    public void hereNow() {
        pubnub.hereNow(channel, hereNowCallback);
    }

    public void presence() {
        try {
            pubnub.presence(channel, presenceCallback);
        } catch (PubnubException e) {
            logger.debug(e.toString());
        }
    }

    public void publish(String message) {
        pubnub.publish(channel, message, new PublishCallback());
    }
}
