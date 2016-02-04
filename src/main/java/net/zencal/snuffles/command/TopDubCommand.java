package net.zencal.snuffles.command;

import net.zencal.snuffles.domain.Track;
import net.zencal.snuffles.domain.User;
import net.zencal.snuffles.domain.dubtrack.payload.DubChatPayload;
import net.zencal.snuffles.service.DubtrackService;
import net.zencal.snuffles.service.TrackService;
import net.zencal.snuffles.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@BotCommand(triggers = { "topdub" })
public class TopDubCommand extends AnnotationConfiguredDubBotCommand {

    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected TrackService trackService;
    @Autowired
    protected UserService userService;

    @Override
    public void execute(DubChatPayload chatPayload) {
        ArrayList<String> params = new ArrayList<>(Arrays.asList(chatPayload.getMessage().split(" ")));

        String type = params.size() > 1 ? params.get(1) : "track";

        // TODO: Enums, get highest ratio instead
        switch(type) {
            case "track":
                Track track = trackService.findTrackByHighestUpdubs();
                dubtrackService.sendMessage("[" + track.getTitle() + "] is the top dub at " + track.getUpdubs());
                break;
            case "user":
                User user = userService.findUserByHighestUpdubsReceived();
                dubtrackService.sendMessage(user.getUsername() + " is the top dub at " + user.getUpdubsReceived());
                break;
        }
    }
}