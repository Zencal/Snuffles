package net.zencal.snuffles.callback;

import com.pubnub.api.PubnubError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class PublishCallback extends CustomCallback {
    protected Logger logger = LogManager.getLogger(PublishCallback.class);

    @Override
    public void successCallback(String channel, Object response) {
        logger.debug("PUBLISH SUCCESS: " + response.toString());
    }

    @Override
    public void errorCallback(String channel, PubnubError error) {
        logger.debug("PUBLISH ERROR: " + error.toString());
    }
}
