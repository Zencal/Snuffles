package net.zencal.snuffles.domain.dubtrack.payload;

import net.zencal.snuffles.domain.dubtrack.DubUser;

public class DubChatPayload {
    protected String chatid;
    protected String message;
    protected String realTimeChannel;
    protected Long time;
    protected String type;
    protected DubUser user;

    public DubChatPayload(String chatid, String message, String realTimeChannel, Long time, String type, DubUser user) {
        this.chatid = chatid;
        this.message = message;
        this.realTimeChannel = realTimeChannel;
        this.time = time;
        this.type = type;
        this.user = user;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRealTimeChannel() {
        return realTimeChannel;
    }

    public void setRealTimeChannel(String realTimeChannel) {
        this.realTimeChannel = realTimeChannel;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DubUser getUser() {
        return user;
    }

    public void setUser(DubUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DubChatPayload{" +
                "chatid='" + chatid + '\'' +
                ", message='" + message + '\'' +
                ", realTimeChannel='" + realTimeChannel + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", user=" + user +
                '}';
    }
}
