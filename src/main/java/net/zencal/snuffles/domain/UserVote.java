package net.zencal.snuffles.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected Integer userId;
    protected String trackId;
    protected Integer targetUserId;
    protected Integer updubs;
    protected Integer downdubs;
    protected Integer grabs;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;

    public UserVote() {
    }

    public UserVote(Integer userId, String trackId, Integer targetUserId) {
        this.userId = userId;
        this.trackId = trackId;
        this.targetUserId = targetUserId;
        this.updubs = 0;
        this.downdubs = 0;
        this.grabs = 0;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public UserVote(Integer userId, String trackId, Integer targetUserId, Integer updubs, Integer downdubs) {
        this.userId = userId;
        this.trackId = trackId;
        this.targetUserId = targetUserId;
        this.updubs = updubs;
        this.downdubs = downdubs;
        this.grabs = 0;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public UserVote(Integer userId, String trackId, Integer targetUserId, Integer updubs, Integer downdubs, Integer grabs) {
        this.userId = userId;
        this.trackId = trackId;
        this.targetUserId = targetUserId;
        this.updubs = updubs;
        this.downdubs = downdubs;
        this.grabs = grabs;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Integer getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Integer targetUserId) {
        this.targetUserId = targetUserId;
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
