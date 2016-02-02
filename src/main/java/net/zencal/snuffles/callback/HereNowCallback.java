package net.zencal.snuffles.callback;

import com.pubnub.api.PubnubError;
import net.zencal.snuffles.service.DubtrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HereNowCallback extends CustomCallback {
    protected Logger logger = LogManager.getLogger(HereNowCallback.class);

    @Autowired
    protected DubtrackService dubtrackService;

    public Integer downDubs;
    public String songId;

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
        logger.debug("SUBSCRIBE : " + channel + " : "
                + message.getClass() + " : " + message.toString());
        JSONObject json = (JSONObject) message;
        Integer usersInRoom = (Integer) json.get("occupancy");
    }

    @Override
    public void errorCallback(String channel, PubnubError error) {
        logger.debug("SUBSCRIBE : ERROR on channel " + channel
                + " : " + error.toString());
    }
}
