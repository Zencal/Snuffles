package net.zencal.snuffles.domain.dubtrack;

public class DubPlaylist {
    protected String _song;
    protected Long created;
    protected Boolean isActive;
    protected String userid;
    protected Long played;
    protected String roomid;
    protected Boolean skipped;
    protected Integer downdubs;
    protected Boolean isPlayed;
    protected Integer __v;
    protected String _id;
    protected Long songLength;
    protected String _user;
    protected Integer updubs;
    protected String songid;
    protected String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
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

    public Integer getDowndubs() {
        return downdubs;
    }

    public void setDowndubs(Integer downdubs) {
        this.downdubs = downdubs;
    }

    public Boolean getSkipped() {
        return skipped;
    }

    public void setSkipped(Boolean skipped) {
        this.skipped = skipped;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String get_song() {
        return _song;
    }

    public void set_song(String _song) {
        this._song = _song;
    }

    public Boolean getIsPlayed() {
        return isPlayed;
    }

    public Long getPlayed() {
        return played;
    }

    public void setIsplayed(Boolean isPlayed) {
        this.isPlayed = isPlayed;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }

    @Override
    public String toString() {
        return "DubPlaylist{" +
                "_song='" + _song + '\'' +
                ", created=" + created +
                ", isActive=" + isActive +
                ", userid='" + userid + '\'' +
                ", played=" + played +
                ", roomid='" + roomid + '\'' +
                ", skipped=" + skipped +
                ", downdubs=" + downdubs +
                ", isPlayed=" + isPlayed +
                ", __v=" + __v +
                ", _id='" + _id + '\'' +
                ", songLength=" + songLength +
                ", _user='" + _user + '\'' +
                ", updubs=" + updubs +
                ", songid='" + songid + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
