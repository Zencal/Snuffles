package net.zencal.snuffles.domain.dubtrack;

public class DubHistory {
    protected String _id;
    protected Long created;
    protected Boolean isActive;
    protected Boolean isPlayed;
    protected Boolean skipped;
    protected Integer order;
    protected String roomid;
    protected Long songLength;
    protected Integer updubs;
    protected Integer downdubs;
    protected String userid;
    protected String songid;
    protected DubUser _user;
    protected DubSong _song;
    protected Integer __v;
    protected Long played;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsPlayed() {
        return isPlayed;
    }

    public Long getPlayed() {
        return played;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }

    public void setIsPlayed(Boolean played) {
        isPlayed = played;
    }

    public Boolean getSkipped() {
        return skipped;
    }

    public void setSkipped(Boolean skipped) {
        this.skipped = skipped;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public Long getSongLength() {
        return songLength;
    }

    public void setSongLength(Long songLength) {
        this.songLength = songLength;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSongid() {
        return songid;
    }

    public void setSongid(String songid) {
        this.songid = songid;
    }

    public DubUser get_user() {
        return _user;
    }

    public void set_user(DubUser _user) {
        this._user = _user;
    }

    public DubSong get_song() {
        return _song;
    }

    public void set_song(DubSong _song) {
        this._song = _song;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    @Override
    public String toString() {
        return "DubHistory{" +
                "_id='" + _id + '\'' +
                ", created=" + created +
                ", isActive=" + isActive +
                ", isPlayed=" + isPlayed +
                ", skipped=" + skipped +
                ", order=" + order +
                ", roomid='" + roomid + '\'' +
                ", songLength=" + songLength +
                ", updubs=" + updubs +
                ", downdubs=" + downdubs +
                ", userid='" + userid + '\'' +
                ", songid='" + songid + '\'' +
                ", _user=" + _user +
                ", _song=" + _song +
                ", __v=" + __v +
                ", played=" + played +
                '}';
    }
}
