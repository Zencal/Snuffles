package net.zencal.snuffles.callback;

import com.pubnub.api.PubnubError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PresenceCallback extends CustomCallback {
    protected Logger logger = LogManager.getLogger(PresenceCallback.class);

    @Override
    public void connectCallback(String channel, Object message) {
        logger.debug("CONNECT on channel:" + channel
                + " : " + message.getClass() + " : "
                + message.toString());
    }

    @Override
    public void disconnectCallback(String channel, Object message) {
        logger.debug("DISCONNECT on channel:" + channel
                + " : " + message.getClass() + " : "
                + message.toString());
    }

    @Override
    public void reconnectCallback(String channel, Object message) {
        logger.debug("RECONNECT on channel:" + channel
                + " : " + message.getClass() + " : "
                + message.toString());
    }

    @Override
    public void successCallback(String channel, Object message) {
        logger.debug("PRESENCE: " + channel + " : "
                + message.getClass() + " : " + message.toString());
    }

    @Override
    public void errorCallback(String channel, PubnubError error) {
        logger.debug("ERROR on channel " + channel
                + " : " + error.toString());
    }
}
