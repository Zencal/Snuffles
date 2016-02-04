package net.zencal.snuffles.domain.dubtrack.payload;

import net.zencal.snuffles.domain.dubtrack.DubRoom;
import net.zencal.snuffles.domain.dubtrack.DubUser;

public class DubUserLeavePayload {
    protected String type;
    protected DubUser user;
    protected DubRoom room;

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

    public DubRoom getRoom() {
        return room;
    }

    public void setRoom(DubRoom room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "DubUserLeavePayload{" +
                "type='" + type + '\'' +
                ", user=" + user +
                ", room=" + room +
                '}';
    }
}
