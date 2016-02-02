package net.zencal.snuffles.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class UserPlay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Long userId;
    protected String trackId;
    protected Long timesPlayed;
    protected Long updubs;
    protected Long downdubs;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;

    public UserPlay() {
    }

    public UserPlay(Long userId, String trackId) {
        this.userId = userId;
        this.trackId = trackId;
        this.timesPlayed = 1L;
        this.updubs = 0L;
        this.downdubs = 0L;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public UserPlay(Long userId, String trackId, Long timesPlayed, Long updubs, Long downdubs, LocalDateTime firstSeen, LocalDateTime lastSeen) {
        this.userId = userId;
        this.trackId = trackId;
        this.timesPlayed = timesPlayed;
        this.updubs = updubs;
        this.downdubs = downdubs;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Long getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(Long timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public Long getUpdubs() {
        return updubs;
    }

    public void setUpdubs(Long updubs) {
        this.updubs = updubs;
    }

    public Long getDowndubs() {
        return downdubs;
    }

    public void setDowndubs(Long downdubs) {
        this.downdubs = downdubs;
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
