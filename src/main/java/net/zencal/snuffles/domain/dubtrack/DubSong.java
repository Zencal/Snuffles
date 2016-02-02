package net.zencal.snuffles.domain.dubtrack;

import java.util.Date;
import java.util.List;

public class DubSong {
    protected String _id;
    protected String name;
    protected String description;
    protected String type;
    protected String fkid;
    protected String streamUrl;
    protected String fileUrl;
    protected String songArtist;
    protected Long songLength;
    protected String songBitrate;
    protected String songMeta;
    protected Date created;
    protected Integer updub;
    protected Integer downdub;
    protected String userid;
    protected Integer __v;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public Long getSongLength() {
        return songLength;
    }

    public void setSongLength(Long songLength) {
        this.songLength = songLength;
    }

    public String getSongBitrate() {
        return songBitrate;
    }

    public void setSongBitrate(String songBitrate) {
        this.songBitrate = songBitrate;
    }

    public String getSongMeta() {
        return songMeta;
    }

    public void setSongMeta(String songMeta) {
        this.songMeta = songMeta;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getUpdub() {
        return updub;
    }

    public void setUpdub(Integer updub) {
        this.updub = updub;
    }

    public Integer getDowndub() {
        return downdub;
    }

    public void setDowndub(Integer downdub) {
        this.downdub = downdub;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "DubSong{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", fkid='" + fkid + '\'' +
                ", streamUrl='" + streamUrl + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", songArtist='" + songArtist + '\'' +
                ", songLength=" + songLength +
                ", songBitrate='" + songBitrate + '\'' +
                ", songMeta='" + songMeta + '\'' +
                ", created=" + created +
                ", updub=" + updub +
                ", downdub=" + downdub +
                ", userid='" + userid + '\'' +
                ", __v=" + __v +
                '}';
    }
}
