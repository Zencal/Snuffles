package net.zencal.snuffles.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="dubuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String username;
    protected Long updubsReceived;
    protected Long downdubsReceived;
    protected Long updubsGiven;
    protected Long downdubsGiven;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;
    protected Long timesSeen;
    protected String dubUserId;

    public User() {
    }

    public User(String username, Long timesSeen, Long updubsReceived, Long downdubsReceived, Long updubsGiven, Long downdubsGiven, LocalDateTime firstSeen, LocalDateTime lastSeen, String dubUserId) {
        this.username = username;
        this.updubsReceived = updubsReceived;
        this.downdubsReceived = downdubsReceived;
        this.updubsGiven = updubsGiven;
        this.downdubsGiven = downdubsGiven;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
        this.timesSeen = timesSeen;
        this.dubUserId = dubUserId;
    }

    public User(String username, String dubUserId) {
        this.username = username;
        this.updubsReceived = 0L;
        this.downdubsReceived = 0L;
        this.updubsGiven = 0L;
        this.downdubsGiven = 0L;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
        this.timesSeen = 1L;
        this.dubUserId = dubUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUpdubsReceived() {
        return updubsReceived;
    }

    public void setUpdubsReceived(Long updubsReceived) {
        this.updubsReceived = updubsReceived;
    }

    public Long getDowndubsReceived() {
        return downdubsReceived;
    }

    public void setDowndubsReceived(Long downdubsReceived) {
        this.downdubsReceived = downdubsReceived;
    }

    public Long getUpdubsGiven() {
        return updubsGiven;
    }

    public void setUpdubsGiven(Long updubsGiven) {
        this.updubsGiven = updubsGiven;
    }

    public Long getDowndubsGiven() {
        return downdubsGiven;
    }

    public void setDowndubsGiven(Long downdubsGiven) {
        this.downdubsGiven = downdubsGiven;
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

    public Long getTimesSeen() {
        return timesSeen;
    }

    public void setTimesSeen(Long timesSeen) {
        this.timesSeen = timesSeen;
    }

    public String getDubUserId() {
        return dubUserId;
    }

    public void setDubUserId(String dubUserId) {
        this.dubUserId = dubUserId;
    }
}
