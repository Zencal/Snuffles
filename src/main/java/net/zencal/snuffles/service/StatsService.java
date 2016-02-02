package net.zencal.snuffles.service;

import net.zencal.snuffles.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class StatsService {
    @Autowired
    protected UserService userService;
    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected UserPlayService userPlayService;

    private Logger logger = LogManager.getLogger(CommandService.class);

    public void sendStatsToDubtrack(String chatMessage, String requestingUsername) {
        ArrayList<String> params = new ArrayList<>(Arrays.asList(chatMessage.split(" ")));

        User user;
        if(StringUtils.equalsIgnoreCase(chatMessage, "!stats")) {
            logger.debug("Getting stats for " + requestingUsername);
            user = userService.findUserByUsername(requestingUsername);
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
                    .append(", receiving ").append(user.getUpdubsReceived()).append(" updubs")
                    .append(" and ").append(user.getDowndubsReceived()).append(" downdubs")
                    .append(" (").append(calculateUpdubPercentage(user.getUpdubsReceived(), user.getDowndubsReceived())).append("% updub rate).");
            StringBuilder votesCast = new StringBuilder(user.getUsername())
                    .append(" has cast ").append(user.getUpdubsGiven()).append(" updubs")
                    .append(" and ").append(user.getDowndubsGiven()).append(" downdubs")
                    .append(" (").append(calculateUpdubPercentage(user.getUpdubsGiven(), user.getDowndubsGiven())).append("% updub rate).");

            dubtrackService.sendMessage(votesReceived.toString());
            dubtrackService.sendMessage(votesCast.toString());
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
