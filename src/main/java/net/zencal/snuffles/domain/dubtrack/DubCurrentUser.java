package net.zencal.snuffles.domain.dubtrack;

public class DubCurrentUser {
    protected String _id;
    protected Long updated;
    protected Long skippedCount;
    protected Long playedCount;
    protected Long songsInQueue;
    protected Boolean active;
    protected Long dubs;
    protected Long order;
    protected String roomid;
    protected String userid;
    protected DubUser _user;
    protected Integer __v;
    protected Boolean authorized;
    protected String ot_token;
    protected DubRole roleid;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public Long getSkippedCount() {
        return skippedCount;
    }

    public void setSkippedCount(Long skippedCount) {
        this.skippedCount = skippedCount;
    }

    public Long getPlayedCount() {
        return playedCount;
    }

    public void setPlayedCount(Long playedCount) {
        this.playedCount = playedCount;
    }

    public Long getSongsInQueue() {
        return songsInQueue;
    }

    public void setSongsInQueue(Long songsInQueue) {
        this.songsInQueue = songsInQueue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getDubs() {
        return dubs;
    }

    public void setDubs(Long dubs) {
        this.dubs = dubs;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
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

    public DubUser get_user() {
        return _user;
    }

    public void set_user(DubUser _user) {
        this._user = _user;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }

    public String getOt_token() {
        return ot_token;
    }

    public void setOt_token(String ot_token) {
        this.ot_token = ot_token;
    }

    public DubRole getRoleid() {
        return roleid;
    }

    public void setRoleid(DubRole roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "DubCurrentUser{" +
                "_id='" + _id + '\'' +
                ", updated=" + updated +
                ", skippedCount=" + skippedCount +
                ", playedCount=" + playedCount +
                ", songsInQueue=" + songsInQueue +
                ", active=" + active +
                ", dubs=" + dubs +
                ", order=" + order +
                ", roomid='" + roomid + '\'' +
                ", userid='" + userid + '\'' +
                ", _user=" + _user +
                ", __v=" + __v +
                ", authorized=" + authorized +
                ", ot_token='" + ot_token + '\'' +
                ", roleid=" + roleid +
                '}';
    }
}
