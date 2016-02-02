package net.zencal.snuffles.callback;

import com.pubnub.api.Callback;
import net.zencal.snuffles.service.DubNubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomCallback extends Callback {
    @Autowired
    protected DubNubService dubNubService;
}
