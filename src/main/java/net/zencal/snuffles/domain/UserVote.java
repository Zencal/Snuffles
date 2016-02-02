package net.zencal.snuffles.domain;

import javax.persistence.*;

@Entity
@Table
public class UserVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected Long userId;
    protected String trackId;
    protected Long targetUserId;
    protected Long updubs;
    protected Long downdubs;

    public UserVote() {
    }

    public UserVote(Long userId, String trackId, Long targetUserId) {
        this.userId = userId;
        this.trackId = trackId;
        this.targetUserId = targetUserId;
        this.updubs = 0L;
        this.downdubs = 0L;
    }

    public UserVote(Long userId, String trackId, Long targetUserId, Long updubs, Long downdubs) {
        this.userId = userId;
        this.trackId = trackId;
        this.targetUserId = targetUserId;
        this.updubs = updubs;
        this.downdubs = downdubs;
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

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
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
}
