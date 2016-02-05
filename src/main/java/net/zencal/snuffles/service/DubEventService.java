package net.zencal.snuffles.service;

import com.google.gson.Gson;
import net.zencal.snuffles.domain.Track;
import net.zencal.snuffles.domain.User;
import net.zencal.snuffles.domain.UserPlay;
import net.zencal.snuffles.domain.dubtrack.*;
import net.zencal.snuffles.domain.dubtrack.payload.DubPlaylistUpdatePayload;
import net.zencal.snuffles.domain.dubtrack.payload.DubUserJoinPayload;
import net.zencal.snuffles.domain.dubtrack.payload.DubUserLeavePayload;
import net.zencal.snuffles.domain.dubtrack.payload.DubVotePayload;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DubEventService {
    private Logger logger = LogManager.getLogger(DubEventService.class);

    @Autowired
    protected DubBotService dubBotService;
    @Autowired
    protected DubtrackService dubtrackService;
    @Autowired
    protected UserService userService;
    @Autowired
    protected TrackService trackService;
    @Autowired
    protected UserPlayService userPlayService;
    @Autowired
    protected UserVoteService userVoteService;

    protected UserPlay currentUserPlay;
    protected Map<String, Boolean> currentVotes;
    protected List<String> currentGrabs;

    @PostConstruct
    public void init() {
        DubActiveTrack dubActiveTrack = dubtrackService.retrieveActiveTrack();
        DubPlaylistSong dubPlaylistSong = dubActiveTrack.getSong();
        String djId = dubPlaylistSong.getUserid();
        String trackId = dubActiveTrack.getSongInfo().get_id();

        User dj = userService.findUserById(djId);
        if(dj == null) {
            dj = userService.createUser(new User(djId, dubtrackService.retrieveUsernameByIdFromDubtrack(djId)));
        }

        if(currentUserPlay == null) {
            currentUserPlay = userPlayService.findUserPlayByUserIdAndTrackId(dj.getId(), trackId);
            if(currentUserPlay == null) {
                currentUserPlay = userPlayService.createUserPlay(new UserPlay(dj.getId(), trackId));
            }
        }

        currentUserPlay.setUpdubs(dubPlaylistSong.getUpdubs());
        currentUserPlay.setDowndubs(dubPlaylistSong.getDowndubs());

        currentGrabs = new ArrayList<>();
        currentVotes = new HashMap<>();
    }

    public void handleEvent(JSONObject json) {
        switch(json.getString("type")) {
            case "user-join":
                handleUserJoin(json);
                break;
            case "user-leave":
                handleUserLeave(json);
                break;
            case "room_playlist-update":
                handleTrackChange(json);
                break;
            case "room_playlist-dub":
                handleVote(json);
                break;
            case "room_playlist-queue-update-grabs":
                handleGrab(json);
                break;
        }
    }

    protected void handleUserJoin(JSONObject json) {
        DubUserJoinPayload dubUserJoinPayload = new Gson().fromJson(json.toString(), DubUserJoinPayload.class);
        DubUser user = dubUserJoinPayload.getUser();
        String username = user.getUsername();
        String userId = user.get_id();

        dubBotService.sendDubtrackUserJoin(username);
        userService.updateLastSeen(username, userId);
        if(StringUtils.equalsIgnoreCase(userId, "56018ca04693ff0300c74b97")) {
            //poondonkus joined
            dubtrackService.shupoon();
        } else if(StringUtils.equalsIgnoreCase(userId, "53fcc4e54517e20200e73b61")) {
            //hoot and patoot
            dubtrackService.hootUp();
        }
    }

    private void handleUserLeave(JSONObject json) {
        try {
            DubUserLeavePayload dubUserLeavePayload = new Gson().fromJson(json.toString(), DubUserLeavePayload.class);
            dubBotService.sendDubtrackUserLeave(dubUserLeavePayload.getUser().getUsername());
        } catch(Exception exception) {
            logger.error(exception);
        }
    }

    private void handleTrackChange(JSONObject json) {
        DubPlaylistUpdatePayload dubPlaylistUpdatePayload = new Gson().fromJson(json.toString(), DubPlaylistUpdatePayload.class);
        logger.debug("TRACK CHANGE EVENT TO TRACKID: " + dubPlaylistUpdatePayload.getSongInfo().get_id() + " WITH START TIME " + dubPlaylistUpdatePayload.getStartTime());

        if(dubPlaylistUpdatePayload.getStartTime() == -1) {
            insertPreviousTrackDetails();

            currentVotes = new HashMap<>();
            currentGrabs = new ArrayList<>();

            insertNewTrackDetails(dubPlaylistUpdatePayload.getSongInfo(), dubPlaylistUpdatePayload.getSong().getUserid());
        }
    }

    private void insertPreviousTrackDetails() {
        if(currentVotes != null && currentGrabs != null && currentUserPlay != null) {
            for (Map.Entry<String, Boolean> vote : currentVotes.entrySet()) {
                String voterUserId = vote.getKey();
                userVoteService.updateOrCreateUserVote(voterUserId, currentUserPlay.getTrackId(), currentUserPlay.getUserId(), vote.getValue());
                if(vote.getValue()) {
                    userService.addUpdubGiven(voterUserId);
                } else {
                    userService.addDowndubGiven(voterUserId);
                }
            }

            for(String voterUserId : currentGrabs) {
                userVoteService.addGrab(voterUserId, currentUserPlay.getTrackId(), currentUserPlay.getUserId());
                userService.addGrabGiven(voterUserId);
            }

            userPlayService.updateUserPlay(currentUserPlay);

            Track track = trackService.findTrackById(currentUserPlay.getTrackId());
            if (track == null) {
                DubSong dubSong = dubtrackService.retrieveTrackInfo(currentUserPlay.getTrackId());
                track = trackService.createTrack(new Track(dubSong.get_id(), dubSong.getType(), dubSong.getFkid(), dubSong.getName()));
            }

            track.setUpdubs(track.getUpdubs() + currentUserPlay.getUpdubs());
            track.setDowndubs(track.getDowndubs() + currentUserPlay.getDowndubs());
            track.setGrabs(track.getGrabs() + currentGrabs.size());
            trackService.updateTrack(track);

            User dj = userService.findUserById(currentUserPlay.getUserId());
            dj.setUpdubsReceived(dj.getUpdubsReceived() + currentUserPlay.getUpdubs());
            dj.setDowndubsReceived(dj.getDowndubsReceived() + currentUserPlay.getDowndubs());
            dj.setGrabbed(dj.getGrabbed() + currentGrabs.size());
            logger.debug("Updating User " + dj.getId() + " with " + dj.getUpdubsReceived() + " updubs received, " + dj.getDowndubsReceived() + " downdubs received and " + dj.getGrabbed() + " grabbed");
            userService.updateUser(dj);
        }
    }

    private void handleGrab(JSONObject json) {
        DubVotePayload dubVotePayload = new Gson().fromJson(json.toString(), DubVotePayload.class);
        DubPlaylist playlist = dubVotePayload.getPlaylist();
        User votingUser = getVotingUser(dubVotePayload.getUser());

        currentUserPlay.setGrabs(playlist.getGrabs());
        currentGrabs.add(votingUser.getId());
        logger.debug("Added userId: " + votingUser.getId() +  "to currentGrabs list");
    }

    private void handleVote(JSONObject json) {
        DubVotePayload dubVotePayload = new Gson().fromJson(json.toString(), DubVotePayload.class);
        DubPlaylist playlist = dubVotePayload.getPlaylist();
        User votingUser = getVotingUser(dubVotePayload.getUser());

        String trackId = playlist.getSongid();
        Integer downDubs = playlist.getDowndubs();
        dubtrackService.checkDownDubs(trackId, downDubs);
        currentUserPlay.setUpdubs(playlist.getUpdubs());
        currentUserPlay.setDowndubs(downDubs);

        currentVotes.put(votingUser.getId(), (StringUtils.equalsIgnoreCase(dubVotePayload.getDubtype(), "updub")));
        logger.debug("Added userId: " + votingUser.getId() + " vote as " + (currentVotes.get(votingUser.getId()) ? "updub" : "downdub") + " to currentVotes map");
    }

    private User getVotingUser(DubUser votingDubUser) {
        String votingUserId = votingDubUser.get_id();
        User votingUser = userService.findUserById(votingUserId);
        if(votingUser == null) {
            votingUser = userService.createUser(new User(votingUserId, votingDubUser.getUsername()));
        }

        return votingUser;
    }

    private void insertNewTrackDetails(DubSong dubSong, String userId) {
        String trackId = dubSong.get_id();
        dubtrackService.sendMessageIfTrackHasBeenPlayedRecently(trackId);
        Track track = trackService.updateTimesPlayed(trackId, dubSong.getType(), dubSong.getFkid(), dubSong.getName());
        User user = userService.findUserById(userId);
        if(user == null) {
            user = userService.createUser(new User(userId, dubtrackService.retrieveUsernameByIdFromDubtrack(userId)));
        }
        currentUserPlay = userPlayService.updateUserPlayTimes(user.getId(), track.getId());
    }
}
