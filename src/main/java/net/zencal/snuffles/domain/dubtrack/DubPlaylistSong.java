package net.zencal.snuffles.domain.dubtrack;

public class DubPlaylistSong {
    protected String _song;
    protected Long created;
    protected Boolean isActive;
    protected String userid;
    protected Long played;
    protected String roomid;
    protected Boolean skipped;
    protected Integer downdubs;
    protected Boolean IsPlayed;
    protected Integer __v;
    protected String _id;
    protected Long songLength;
    protected String _user;
    protected Integer updubs;
    protected String songid;
    protected Integer order;

    public String get_song() {
        return _song;
    }

    public void set_song(String _song) {
        this._song = _song;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Long getIsPlayed() {
        return played;
    }

    public void setIsPlayed(Boolean played) {
        IsPlayed = played;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getSongLength() {
        return songLength;
    }

    public void setSongLength(Long songLength) {
        this.songLength = songLength;
    }

    public String get_user() {
        return _user;
    }

    public void set_user(String _user) {
        this._user = _user;
    }

    public Integer getUpdubs() {
        return updubs;
    }

    public void setUpdubs(Integer updubs) {
        this.updubs = updubs;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public Boolean getSkipped() {
        return skipped;
    }

    public void setSkipped(Boolean skipped) {
        this.skipped = skipped;
    }

    public Integer getDowndubs() {
        return downdubs;
    }

    public void setDowndubs(Integer downdubs) {
        this.downdubs = downdubs;
    }

    @Override
    public String toString() {
        return "DubPlaylistSong{" +
                "_song='" + _song + '\'' +
                ", created=" + created +
                ", isActive=" + isActive +
                ", userid='" + userid + '\'' +
                ", played=" + played +
                ", roomid='" + roomid + '\'' +
                ", skipped=" + skipped +
                ", downdubs=" + downdubs +
                ", IsPlayed=" + IsPlayed +
                ", __v=" + __v +
                ", _id='" + _id + '\'' +
                ", songLength=" + songLength +
                ", _user='" + _user + '\'' +
                ", updubs=" + updubs +
                ", songid='" + songid + '\'' +
                ", order=" + order +
                '}';
    }
}
