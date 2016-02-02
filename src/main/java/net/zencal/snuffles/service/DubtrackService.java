package net.zencal.snuffles.service;

import net.zencal.snuffles.callback.HereNowCallback;
import net.zencal.snuffles.domain.dubtrack.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DubtrackService {
    @Autowired
    protected HereNowCallback hereNowCallback;
    @Autowired
    protected DubNubService dubNubService;

    private Logger logger = LogManager.getLogger(DubtrackService.class);

    @Value(("${dubtrack.room}"))
    protected String dubtrackRoom;
    @Value(("${dubtrack.username}"))
    protected String dubtrackUsername;
    @Value(("${dubtrack.password}"))
    protected String dubtrackPassword;

    @Value(("${skip.duplicate}"))
    private Boolean skipForDuplicate;

    @Value(("${skip.downdubs}"))
    private Boolean skipForDownDubs;
    @Value(("${min.users.downdubs}"))
    private Integer minUsersToForDownDubs;
    @Value(("${percentage.downdubs}"))
    private Integer percentageRequired;

    private RestTemplate restTemplate;
    private HttpHeaders requestHeaders;
    private DubUser user;
    private DubRoom room;


    public void init() {
        restTemplate = new RestTemplate();
        logger.debug("Logging into dub");
        HttpEntity<DubLoginResponse> response = restTemplate.exchange("https://api.dubtrack.fm/auth/dubtrack?password=" + dubtrackPassword + "&username=" + dubtrackUsername, HttpMethod.POST, null, DubLoginResponse.class);
        logger.debug(response);
        HttpHeaders responseHeaders = response.getHeaders();
        requestHeaders = new HttpHeaders();

        logger.debug("Getting user data");
        HttpEntity<DubUserResponse> dubUserResponse = restTemplate.exchange("https://api.dubtrack.fm/user/" + dubtrackUsername, HttpMethod.GET, null, DubUserResponse.class);
        logger.debug(dubUserResponse);
        user = dubUserResponse.getBody().getData();

        updateRoomDetails();

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.ALL);
        requestHeaders.setAccept(mediaTypes);
        requestHeaders.setConnection("keep-alive");
        requestHeaders.setContentLength(780);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        String setCookie = responseHeaders.get("Set-Cookie").get(0);
        String cookie = setCookie.substring(0, setCookie.indexOf(";"));
        requestHeaders.add("Cookie", cookie);
    }

    public void sendMessage(String message) {
        logger.debug("Sending message");
        DubChatPayload dubChatPayload = new DubChatPayload(null, message, "dubtrackfm-".concat(dubtrackRoom), new Date().getTime(),"chat-message", user);
        HttpEntity<String> newResponse = restTemplate.exchange("https://api.dubtrack.fm/chat/" + room.get_id(), HttpMethod.POST, new HttpEntity<DubChatPayload>(dubChatPayload, requestHeaders), String.class);
        logger.debug(newResponse);
    }

    public List<DubCurrentUser> retrieveUserList() {
        logger.debug("Retrieving user list");
        HttpEntity<DubUserListResponse> dubUserListResponse = restTemplate.exchange("https://api.dubtrack.fm/room/" + room.get_id() + "/users", HttpMethod.GET, null, DubUserListResponse.class);
        logger.debug(dubUserListResponse);
        return dubUserListResponse.getBody().getData();
    }

    public List<DubHistory> retrieveRoomHistory() {
        logger.debug("Retrieving room history");
        HttpEntity<DubHistoryResponse> dubHistoryResponse = restTemplate.exchange("https://api.dubtrack.fm/room/" + room.get_id() + "/playlist/history", HttpMethod.GET, null, DubHistoryResponse.class);
        logger.debug(dubHistoryResponse);
        return dubHistoryResponse.getBody().getData();
    }

    public DubHistory retrieveIfTrackHasBeenPlayedRecently(String trackId) {
        logger.debug("Checking if song has been played recently");
        List<DubHistory> historyList = retrieveRoomHistory();
        for(DubHistory history : historyList) {
            if(StringUtils.equalsIgnoreCase(trackId, history.getSongid())) {
                return history;
            }
        }
        return null;
    }

    public void sendMessageIfTrackHasBeenPlayedRecently(String songId) {
        DubHistory history = retrieveIfTrackHasBeenPlayedRecently(songId);
         if(history != null) {
            DubSong song = history.get_song();
            String timeSince = getTimeSinceLastPlayed(new Date(history.getPlayed()));
            StringBuilder message = new StringBuilder("@mods Track: [")
                    .append(song.getName())
                    .append("] was played ")
                    .append(timeSince)
                    .append(" ago by ")
                    .append(history.get_user().getUsername());
             sendMessage(message.toString());
             if(skipForDuplicate) {
                 skipPlayingTrack(songId);
             }
        }
    }

    private String getTimeSinceLastPlayed(Date played) {
        Date now = new Date();
        Long timeBetween = now.getTime() - played.getTime();
        Long minutes = timeBetween / (60 * 1000);
        return minutes + " minutes";
    }

    public void checkDownDubs(String songId, Long downDubs) {
        updateRoomDetails();
        Integer usersInRoom = room.getActiveUsers();
        if((usersInRoom > minUsersToForDownDubs) && (downDubs > (usersInRoom * percentageRequired / 100))) {
            sendMessage("Over " + percentageRequired + "% of users have downvoted");
            if(skipForDownDubs) {
                skipPlayingTrack(songId);
            }
        }
    }

    private void updateRoomDetails() {
        logger.debug("Getting room data");
        HttpEntity<DubRoomResponse> dubRoomResponse = restTemplate.exchange("https://api.dubtrack.fm/room/" + dubtrackRoom, HttpMethod.GET, null, DubRoomResponse.class);
        logger.debug(dubRoomResponse);
        room = dubRoomResponse.getBody().getData();
    }

    public void skipPlayingTrack(String songId) {
        logger.debug("Skipping track");
        HttpEntity<String> response = restTemplate.exchange("https://api.dubtrack.fm/chat/skip/" + room.get_id() + "/" + songId, HttpMethod.POST, new HttpEntity<Object>(null, requestHeaders), String.class);
        logger.debug(response.getBody());
    }

    public String printUserList() {
        StringBuilder userList = new StringBuilder("Users: ");

        for(DubCurrentUser currentUser : retrieveUserList()) {
            userList.append(currentUser.get_user().getUsername()).append(",");
        }

        return userList.toString();
    }

    public void sendBTFO() {
        sendMessage("B T F O");
        sendMessage("T");
        sendMessage("F");
        sendMessage("O");
    }

    public void hootUp() {
        sendMessage("\u2002,\u2002\u2002\u2002\u2002\u2002,\u2002\u2002\u2002 IT IS TIME");
        sendMessage("\u2002)\\___/(\u2002\u2002\u2002 TO HOOT");
        sendMessage("\u2002{@)v(@}\u2002 AND PATOOT");
        sendMessage("\u2002{|~~~~|}\u2002\u2002 THE FUCK");
        sendMessage("\u2002{/^^^\\}\u2002\u2002\u2002 UP");
        sendMessage("\u2002`m-m`");
    }

    public void shupoon() {
        sendMessage(":sparkles: shupoon~ :sparkles:");
    }

    public String retrieveUsernameByDubUserIdFromDubtrack(String dubUserId) {
        return retrieveUserByDubUserIdFromDubtrack(dubUserId).getUsername();
    }

    private DubUser retrieveUserByDubUserIdFromDubtrack(String dubUserId) {
        logger.debug("Retrieving user from dubtrack");
        HttpEntity<DubUserResponse> response = restTemplate.exchange("https://api.dubtrack.fm/user/" + dubUserId, HttpMethod.GET, null, DubUserResponse.class);
        logger.debug(response.getBody());
        return response.getBody().getData();
    }

    public DubSong retrieveTrackInfo(String trackId) {
        logger.debug("Retrieving user from dubtrack");
        HttpEntity<DubSongResponse> response = restTemplate.exchange("https://api.dubtrack.fm/song/" + trackId, HttpMethod.GET, null, DubSongResponse.class);
        logger.debug(response.getBody());
        return response.getBody().getData();
    }
}
