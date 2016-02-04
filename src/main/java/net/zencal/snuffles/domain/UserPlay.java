package net.zencal.snuffles.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class UserPlay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String userId;
    protected String trackId;
    protected Integer timesPlayed;
    protected Integer updubs;
    protected Integer downdubs;
    protected Integer grabs;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;

    public UserPlay() {
    }

    public UserPlay(String userId, String trackId) {
        this.userId = userId;
        this.trackId = trackId;
        this.timesPlayed = 1;
        this.updubs = 0;
        this.downdubs = 0;
        this.grabs = 0;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public UserPlay(String userId, String trackId, Integer timesPlayed, Integer updubs, Integer downdubs, Integer grabs, LocalDateTime firstSeen, LocalDateTime lastSeen) {
        this.userId = userId;
        this.trackId = trackId;
        this.timesPlayed = timesPlayed;
        this.updubs = updubs;
        this.downdubs = downdubs;
        this.grabs = grabs;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Integer getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(Integer timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public Integer getUpdubs() {
        return updubs;
    }

    public void setUpdubs(Integer updubs) {
        this.updubs = updubs;
    }

    public Integer getDowndubs() {
        return downdubs;
    }

    public void setDowndubs(Integer downdubs) {
        this.downdubs = downdubs;
    }

    public Integer getGrabs() {
        return grabs;
    }

    public void setGrabs(Integer grabs) {
        this.grabs = grabs;
    }

    public LocalDateTime getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(LocalDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }
}
