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
    protected Integer timesPlayed;
    protected Integer updubs;
    protected Integer downdubs;
    protected Integer grabs;
    protected LocalDateTime firstSeen;
    protected LocalDateTime lastSeen;

    public Track() {
    }

    public Track(String id, String type, String fkid, String title) {
        this.id = id;
        this.type = type;
        this.fkid = fkid;
        this.title = title;
        this.timesPlayed = 1;
        this.updubs = 0;
        this.downdubs = 0;
        this.grabs = 0;
        this.firstSeen = LocalDateTime.now();
        this.lastSeen = LocalDateTime.now();
    }

    public Track(String id, String type, String fkid, String title, Integer timesPlayed, Integer updubs, Integer downdubs, Integer grabs, LocalDateTime firstSeen, LocalDateTime lastSeen) {
        this.id = id;
        this.type = type;
        this.fkid = fkid;
        this.title = title;
        this.timesPlayed = timesPlayed;
        this.updubs = updubs;
        this.downdubs = downdubs;
        this.grabs = grabs;
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
