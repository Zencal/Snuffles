package net.zencal.snuffles.service;

import net.zencal.snuffles.domain.Track;
import net.zencal.snuffles.domain.User;
import net.zencal.snuffles.domain.UserPlay;
import net.zencal.snuffles.domain.dubtrack.DubSong;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DubEventService {
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
    protected Map<Integer, Boolean> currentVotes;
    protected List<Integer> currentGrabs;

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
        JSONObject user = json.getJSONObject("user");
        String username = user.getString("username");
        dubBotService.sendDubtrackUserJoin(username);
        userService.updateLastSeen(username, user.getString("_id"));
        if(StringUtils.equalsIgnoreCase(username, "poondonkus")) {
            dubtrackService.shupoon();
        } else if(StringUtils.equalsIgnoreCase(username, "mrhoot")) {
            dubtrackService.hootUp();
        }
    }

    private void handleUserLeave(JSONObject json) {
        JSONObject user = json.getJSONObject("user");
        dubBotService.sendDubtrackUserLeave(user.getString("username"));
    }

    private void handleTrackChange(JSONObject json) {
        /*{"song":{"_song":"566393c14d7e2d5f00269a23","created":1453957198111,"isActive":true,"userid":"54d68b035ebafa030039a38f","played":1454284754803,"roomid":"53f7d4960451940200ffec4f","skipped":false,"downdubs":0,"isPlayed":false,"__v":0,"_id":"56a9a04ecb4e6885043a96f7","songLength":184000,"_user":"54d68b035ebafa030039a38f","updubs":0,"songid":"566393c14d7e2d5f00269a23","order":14},"startTime":-1,"type":"room_playlist-update","songInfo":{"images":{"youtube":{"standard":{"width":640,"url":"https://i.ytimg.com/vi/i_GP1tt6_Fk/sddefault.jpg","height":480},"default":{"width":120,"url":"https://i.ytimg.com/vi/i_GP1tt6_Fk/default.jpg","height":90},"high":{"width":480,"url":"https://i.ytimg.com/vi/i_GP1tt6_Fk/hqdefault.jpg","height":360},"maxres":{"width":1280,"url":"https://i.ytimg.com/vi/i_GP1tt6_Fk/maxresdefault.jpg","height":720},"medium":{"width":320,"url":"https://i.ytimg.com/vi/i_GP1tt6_Fk/mqdefault.jpg","height":180}},"thumbnail":"https://i.ytimg.com/vi/i_GP1tt6_Fk/hqdefault.jpg"},"updub":0,"created":1449366465224,"downdub":0,"__v":0,"name":"OC ReMix #2862: Kirby&apos;s Dream Land &apos;Cabbage Salad&apos; [Green Greens, Castle Lololo] by Y","description":null,"_id":"566393c14d7e2d5f00269a23","songLength":184000,"type":"youtube","userid":null,"fkid":"i_GP1tt6_Fk"}}*/
        insertPreviousTrackDetails();

        JSONObject song = json.getJSONObject("song");
        JSONObject trackInfo = json.getJSONObject("songInfo");
        currentVotes = new HashMap<>();
        currentGrabs = new ArrayList<>();

        insertNewTrackDetails(song.getString("songid"), trackInfo, song.getString("userid"));
    }

    private void insertPreviousTrackDetails() {
        if(currentVotes != null && currentGrabs != null && currentUserPlay != null) {
            for (Map.Entry<Integer, Boolean> vote : currentVotes.entrySet()) {
                Integer voterUserId = vote.getKey();
                userVoteService.updateOrCreateUserVote(voterUserId, currentUserPlay.getTrackId(), currentUserPlay.getUserId(), vote.getValue());
                if(vote.getValue()) {
                    userService.addUpdubGiven(voterUserId);
                } else {
                    userService.addDowndubGiven(voterUserId);
                }
            }

            for(Integer voterUserId : currentGrabs) {
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
            userService.updateUser(dj);
        }
    }

    private void handleGrab(JSONObject json) {
        JSONObject playlist = (JSONObject) json.get("playlist");
        User votingUser = getVotingUser(json);

        if(currentGrabs == null) {
            currentGrabs = new ArrayList<>();
        }

        currentUserPlay.setGrabs(playlist.getInt("grabs"));
        currentGrabs.add(votingUser.getId());
    }

    private void handleVote(JSONObject json) {
        User votingUser = getVotingUser(json);
        JSONObject playlist = (JSONObject) json.get("playlist");
        String trackId = playlist.getString("songid");

        dubtrackService.checkDownDubs(trackId, playlist.getLong("downdubs"));
        currentUserPlay.setUpdubs(playlist.getInt("updubs"));
        currentUserPlay.setDowndubs(playlist.getInt("downdubs"));
        currentUserPlay.setGrabs(playlist.getInt("grabs"));
        if(currentVotes == null) {
            currentVotes = new HashMap<>();
        }
        currentVotes.put(votingUser.getId(), (StringUtils.equalsIgnoreCase(json.getString("dubtype"), "updub")));
    }

    private User getVotingUser(JSONObject json) {
        JSONObject playlist = (JSONObject) json.get("playlist");
        String djDubId = playlist.getString("userid");
        JSONObject jsonUser = (JSONObject) json.get("user");
        String votingUserUsername = jsonUser.getString("username");
        String trackId = playlist.getString("songid");

        User votingUser = userService.findUserByUsername(votingUserUsername);
        if(votingUser == null) {
            votingUser = userService.createUser(new User(votingUserUsername, jsonUser.getString("_id")));
        }
        User dj = userService.findUserByDubUserId(djDubId);
        if(dj == null) {
            dj = userService.createUser(new User(dubtrackService.retrieveUsernameByDubUserIdFromDubtrack(djDubId), djDubId));
        }

        if(currentUserPlay == null) {
            currentUserPlay = userPlayService.findUserPlayByUserIdAndTrackId(dj.getId(), trackId);
            if(currentUserPlay == null) {
                currentUserPlay = userPlayService.createUserPlay(new UserPlay(dj.getId(), trackId));
            }
        }
        return votingUser;
    }

    private void insertNewTrackDetails(String trackId, JSONObject trackInfo, String userId) {
        dubtrackService.sendMessageIfTrackHasBeenPlayedRecently(trackId);
        Track track = trackService.updateTimesPlayed(trackId, trackInfo.getString("type"), trackInfo.getString("fkid"), trackInfo.getString("name"));
        User user = userService.findUserByDubUserId(userId);
        if(user == null) {
            user = userService.createUser(new User(dubtrackService.retrieveUsernameByDubUserIdFromDubtrack(userId), userId));
        }
        currentUserPlay = userPlayService.updateUserPlayTimes(user.getId(), track.getId());
    }
}
