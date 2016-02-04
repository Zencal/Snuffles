package net.zencal.snuffles.domain.dubtrack.payload;

import net.zencal.snuffles.domain.dubtrack.DubJoinUser;
import net.zencal.snuffles.domain.dubtrack.DubUser;

public class DubUserJoinPayload {
    protected String type;
    protected DubJoinUser roomUser;
    protected DubUser user;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DubJoinUser getRoomUser() {
        return roomUser;
    }

    public void setRoomUser(DubJoinUser roomUser) {
        this.roomUser = roomUser;
    }

    public DubUser getUser() {
        return user;
    }

    public void setUser(DubUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DubUserJoinPayload{" +
                "type='" + type + '\'' +
                ", roomUser=" + roomUser +
                ", user=" + user +
                '}';
    }
}
