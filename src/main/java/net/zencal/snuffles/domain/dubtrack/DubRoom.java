package net.zencal.snuffles.domain.dubtrack;

public class DubRoom {
    protected String _id;
    protected Integer __v;
    protected DubUser _user;
    protected Integer activeUsers;
    protected Integer allowedDjs;
    protected Long created;
    protected DubSong currentSong;
    protected String description;
    protected Boolean displayQueue;
    protected Boolean isPublic;
    protected String lang;
    protected Integer maxLengthSong;
    protected String musicType;
    protected String name;
    protected String otSession;
    protected String password;
    protected String realTimeChannel;
    protected String roomEmbed;
    protected String roomType;
    protected String roomUrl;
    protected Integer status;
    protected Long updated;
    protected String userid;
    protected Boolean lockQueue;
    protected String welcomeMessage;
    protected String metaDescription;
    protected Boolean displayInLobby;
    protected Boolean displayInSearch;
    protected String roomDisplay;
    protected Boolean limitQueueLength;
    protected Integer timeSongQueueRepeat;
    protected Boolean recycleDJ;
    protected Boolean displayDJinQueue;
    protected Boolean displayUserJoin;
    protected Boolean displayUserLeave;
    protected Boolean displayUserGrab;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public DubUser get_user() {
        return _user;
    }

    public void set_user(DubUser _user) {
        this._user = _user;
    }

    public Integer getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(Integer activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Integer getAllowedDjs() {
        return allowedDjs;
    }

    public void setAllowedDjs(Integer allowedDjs) {
        this.allowedDjs = allowedDjs;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public DubSong getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(DubSong currentSong) {
        this.currentSong = currentSong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDisplayQueue() {
        return displayQueue;
    }

    public void setDisplayQueue(Boolean displayQueue) {
        this.displayQueue = displayQueue;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getMaxLengthSong() {
        return maxLengthSong;
    }

    public void setMaxLengthSong(Integer maxLengthSong) {
        this.maxLengthSong = maxLengthSong;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtSession() {
        return otSession;
    }

    public void setOtSession(String otSession) {
        this.otSession = otSession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealTimeChannel() {
        return realTimeChannel;
    }

    public void setRealTimeChannel(String realTimeChannel) {
        this.realTimeChannel = realTimeChannel;
    }

    public String getRoomEmbed() {
        return roomEmbed;
    }

    public void setRoomEmbed(String roomEmbed) {
        this.roomEmbed = roomEmbed;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Boolean getLockQueue() {
        return lockQueue;
    }

    public void setLockQueue(Boolean lockQueue) {
        this.lockQueue = lockQueue;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Boolean getDisplayInLobby() {
        return displayInLobby;
    }

    public void setDisplayInLobby(Boolean displayInLobby) {
        this.displayInLobby = displayInLobby;
    }

    public Boolean getDisplayInSearch() {
        return displayInSearch;
    }

    public void setDisplayInSearch(Boolean displayInSearch) {
        this.displayInSearch = displayInSearch;
    }

    public String getRoomDisplay() {
        return roomDisplay;
    }

    public void setRoomDisplay(String roomDisplay) {
        this.roomDisplay = roomDisplay;
    }

    public Boolean getLimitQueueLength() {
        return limitQueueLength;
    }

    public void setLimitQueueLength(Boolean limitQueueLength) {
        this.limitQueueLength = limitQueueLength;
    }

    public Integer getTimeSongQueueRepeat() {
        return timeSongQueueRepeat;
    }

    public void setTimeSongQueueRepeat(Integer timeSongQueueRepeat) {
        this.timeSongQueueRepeat = timeSongQueueRepeat;
    }

    public Boolean getRecycleDJ() {
        return recycleDJ;
    }

    public void setRecycleDJ(Boolean recycleDJ) {
        this.recycleDJ = recycleDJ;
    }

    public Boolean getDisplayDJinQueue() {
        return displayDJinQueue;
    }

    public void setDisplayDJinQueue(Boolean displayDJinQueue) {
        this.displayDJinQueue = displayDJinQueue;
    }

    public Boolean getDisplayUserJoin() {
        return displayUserJoin;
    }

    public void setDisplayUserJoin(Boolean displayUserJoin) {
        this.displayUserJoin = displayUserJoin;
    }

    public Boolean getDisplayUserLeave() {
        return displayUserLeave;
    }

    public void setDisplayUserLeave(Boolean displayUserLeave) {
        this.displayUserLeave = displayUserLeave;
    }

    public Boolean getDisplayUserGrab() {
        return displayUserGrab;
    }

    public void setDisplayUserGrab(Boolean displayUserGrab) {
        this.displayUserGrab = displayUserGrab;
    }

    @Override
    public String toString() {
        return "DubRoom{" +
                "_id='" + _id + '\'' +
                ", __v=" + __v +
                ", _user=" + _user +
                ", activeUsers=" + activeUsers +
                ", allowedDjs=" + allowedDjs +
                ", created=" + created +
                ", currentSong=" + currentSong +
                ", displayQueue=" + displayQueue +
                ", isPublic=" + isPublic +
                ", lang='" + lang + '\'' +
                ", maxLengthSong=" + maxLengthSong +
                ", musicType='" + musicType + '\'' +
                ", name='" + name + '\'' +
                ", otSession='" + otSession + '\'' +
                ", password='" + password + '\'' +
                ", realTimeChannel='" + realTimeChannel + '\'' +
                ", roomEmbed='" + roomEmbed + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomUrl='" + roomUrl + '\'' +
                ", status=" + status +
                ", updated=" + updated +
                ", userid='" + userid + '\'' +
                ", lockQueue=" + lockQueue +
                ", welcomeMessage='" + welcomeMessage + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                ", displayInLobby=" + displayInLobby +
                ", displayInSearch=" + displayInSearch +
                ", roomDisplay='" + roomDisplay + '\'' +
                ", limitQueueLength=" + limitQueueLength +
                ", timeSongQueueRepeat=" + timeSongQueueRepeat +
                ", recycleDJ=" + recycleDJ +
                ", displayDJinQueue=" + displayDJinQueue +
                ", displayUserJoin=" + displayUserJoin +
                ", displayUserLeave=" + displayUserLeave +
                ", displayUserGrab=" + displayUserGrab +
                '}';
    }
}
