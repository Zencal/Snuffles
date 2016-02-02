package net.zencal.snuffles.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class Track {
    @Id
    protected String id;

    protected String type;
    protected String fkid;
    protected String title;
    protected Long timesPlayed;
    protected Long updubs;
    protected Long downdubs;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;

    public Track() {
    }

    public Track(String id, String type, String fkid, String title) {
        this.id = id;
        this.type = type;
        this.fkid = fkid;
        this.title = title;
        this.timesPlayed = 1L;
        this.updubs = 0L;
        this.downdubs = 0L;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public Track(String id, String type, String fkid, String title, Long timesPlayed, Long updubs, Long downdubs, LocalDateTime firstSeen, LocalDateTime lastSeen) {
        this.id = id;
        this.type = type;
        this.fkid = fkid;
        this.title = title;
        this.timesPlayed = timesPlayed;
        this.updubs = updubs;
        this.downdubs = downdubs;
        this.firstSeen = firstSeen;
        this.lastSeen = lastSeen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFkid() {
        return fkid;
    }

    public void setFkid(String fkid) {
        this.fkid = fkid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
