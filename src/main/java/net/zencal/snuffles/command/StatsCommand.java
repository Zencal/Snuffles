package net.zencal.snuffles.command;

import net.zencal.snuffles.domain.User;
import net.zencal.snuffles.domain.dubtrack.payload.DubChatPayload;
import net.zencal.snuffles.service.DubtrackService;
import net.zencal.snuffles.service.UserPlayService;
import net.zencal.snuffles.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
@BotCommand(triggers = { "stats" })
public class StatsCommand extends AnnotationConfiguredDubBotCommand {
    private Logger logger = LogManager.getLogger(StatsCommand.class);

    @Autowired
    protected UserService userService;
    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected UserPlayService userPlayService;

    @Override
    public void execute(DubChatPayload chatPayload) {
        ArrayList<String> params = new ArrayList<>(Arrays.asList(chatPayload.getMessage().split(" ")));

        User user;
        if(StringUtils.equalsIgnoreCase(chatPayload.getMessage(), "!stats")) {
            logger.debug("Getting stats for " + chatPayload.getUser().getUsername());
            user = userService.findUserByUsername(chatPayload.getUser().getUsername());
        } else {
            String username = params.get(1);
            if(username.indexOf("@") == 0) {
                username = username.substring(1);
            }
            logger.debug("Getting stats for " + username);
            user = userService.findUserByUsername(username);
        }

        if(user != null) {
            StringBuilder votesReceived = new StringBuilder(user.getUsername())
                    .append(" has played ").append(userPlayService.countTracksPlayedByUserId(user.getId())).append(" songs to Snuffles,")
                    .append(" receiving ").append(user.getUpdubsReceived()).append(" updubs")
                    .append(" and ").append(user.getDowndubsReceived()).append(" downdubs")
                    .append(" (").append(calculateUpdubPercentage(user.getUpdubsReceived(), user.getDowndubsReceived())).append("% updub rate).");
            StringBuilder votesCast = new StringBuilder(user.getUsername())
                    .append(" has cast ").append(user.getUpdubsGiven()).append(" updubs")
                    .append(" and ").append(user.getDowndubsGiven()).append(" downdubs")
                    .append(" (").append(calculateUpdubPercentage(user.getUpdubsGiven(), user.getDowndubsGiven())).append("% updub rate).");
            StringBuilder grabs = new StringBuilder(user.getUsername())
                    .append(" has had their songs grabbed ").append(user.getGrabbed()).append(" times")
                    .append(" and has grabbed ").append(user.getGrabbed()).append(" other users songs")
                    .append(" (").append(calculateUpdubPercentage(user.getGrabbed(), user.getGrabs())).append("% grabbed rate).");

            dubtrackService.sendMessage(votesReceived.toString());
            dubtrackService.sendMessage(votesCast.toString());
            dubtrackService.sendMessage(grabs.toString());
        } else {
            dubtrackService.sendMessage("Can't find that user");
        }
    }

    private Double calculateUpdubPercentage(Integer updubs, Integer downdubs) {
        Double percentage = 0.0;
        if(updubs > 0) {
            percentage = Double.valueOf(updubs) / (Double.valueOf(updubs) + Double.valueOf(downdubs));
        }
        return (double) Math.round((percentage * 1000) / 10);
    }
}
