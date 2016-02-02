package net.zencal.snuffles.callback;

import com.pubnub.api.PubnubError;
import net.zencal.snuffles.service.CommandService;
import net.zencal.snuffles.service.DubBotService;
import net.zencal.snuffles.service.DubEventService;
import net.zencal.snuffles.service.DubtrackService;
import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubscribeCallback extends CustomCallback {
    protected Logger logger = LogManager.getLogger(SubscribeCallback.class);
    @Autowired
    protected DubBotService dubBotService;
    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected CommandService commandService;
    @Autowired
    protected DubEventService dubEventService;

    @Override
    public void connectCallback(String channel, Object message) {
        logger.debug("SUBSCRIBE : CONNECTED on channel:" + channel + " : " + message.getClass() + " : " + message.toString());
    }

    @Override
    public void disconnectCallback(String channel, Object message) {
        logger.debug("SUBSCRIBE : DISCONNECT on channel:" + channel
                + " : " + message.getClass() + " : "
                + message.toString());
    }

    @Override
    public void reconnectCallback(String channel, Object message) {
        logger.debug("SUBSCRIBE : RECONNECT on channel:" + channel
                + " : " + message.getClass() + " : "
                + message.toString());
    }

    @Override
    public void successCallback(String channel, Object message) {
        logger.trace("SUBSCRIBE : " + channel + " : "
                + message.getClass() + " : " + message.toString());
        JSONObject json = (JSONObject) message;

        if(json.has("chatid")) {
            commandService.handleCommand(json);
        } else if(json.has("type")) {
            dubEventService.handleEvent(json);
        }

    }

    @Override
    public void errorCallback(String channel, PubnubError error) {
        logger.debug("SUBSCRIBE : ERROR on channel " + channel
                + " : " + error.toString());
    }
}
