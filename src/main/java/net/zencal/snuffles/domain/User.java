package net.zencal.snuffles.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="dubuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String username;
    protected Integer updubsReceived;
    protected Integer downdubsReceived;
    protected Integer grabbed;
    protected Integer updubsGiven;
    protected Integer downdubsGiven;
    protected Integer grabs;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;
    protected Integer timesSeen;
    protected String dubUserId;

    public User() {
    }

    public User(String username, Integer timesSeen, Integer updubsReceived, Integer downdubsReceived, Integer grabbed, Integer updubsGiven, Integer downdubsGiven, Integer grabs, LocalDateTime firstSeen, LocalDateTime lastSeen, String dubUserId) {
        this.username = username;
        this.updubsReceived = updubsReceived;
        this.downdubsReceived = downdubsReceived;
        this.grabbed = grabbed;
        this.updubsGiven = updubsGiven;
        this.downdubsGiven = downdubsGiven;
        this.grabs = grabs;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
        this.timesSeen = timesSeen;
        this.dubUserId = dubUserId;
    }

    public User(String username, String dubUserId) {
        this.username = username;
        this.updubsReceived = 0;
        this.downdubsReceived = 0;
        this.grabbed = 0;
        this.updubsGiven = 0;
        this.downdubsGiven = 0;
        this.grabs = 0;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
        this.timesSeen = 1;
        this.dubUserId = dubUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUpdubsReceived() {
        return updubsReceived;
    }

    public void setUpdubsReceived(Integer updubsReceived) {
        this.updubsReceived = updubsReceived;
    }

    public Integer getDowndubsReceived() {
        return downdubsReceived;
    }

    public void setDowndubsReceived(Integer downdubsReceived) {
        this.downdubsReceived = downdubsReceived;
    }

    public Integer getGrabbed() {
        return grabbed;
    }

    public void setGrabbed(Integer grabbed) {
        this.grabbed = grabbed;
    }

    public Integer getUpdubsGiven() {
        return updubsGiven;
    }

    public void setUpdubsGiven(Integer updubsGiven) {
        this.updubsGiven = updubsGiven;
    }

    public Integer getDowndubsGiven() {
        return downdubsGiven;
    }

    public void setDowndubsGiven(Integer downdubsGiven) {
        this.downdubsGiven = downdubsGiven;
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

    public Integer getTimesSeen() {
        return timesSeen;
    }

    public void setTimesSeen(Integer timesSeen) {
        this.timesSeen = timesSeen;
    }

    public String getDubUserId() {
        return dubUserId;
    }

    public void setDubUserId(String dubUserId) {
        this.dubUserId = dubUserId;
    }
}
