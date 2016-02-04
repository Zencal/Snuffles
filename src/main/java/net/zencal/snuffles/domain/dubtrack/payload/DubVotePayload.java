package net.zencal.snuffles.domain.dubtrack.payload;

import net.zencal.snuffles.domain.dubtrack.DubPlaylist;
import net.zencal.snuffles.domain.dubtrack.DubUser;

public class DubVotePayload {
    protected DubPlaylist playlist;
    protected String dubtype;
    protected String type;
    protected DubUser user;

    public DubPlaylist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(DubPlaylist playlist) {
        this.playlist = playlist;
    }

    public String getDubtype() {
        return dubtype;
    }

    public void setDubtype(String dubtype) {
        this.dubtype = dubtype;
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
        return "DubVotePayload{" +
                "playlist=" + playlist +
                ", dubtype='" + dubtype + '\'' +
                ", type='" + type + '\'' +
                ", user=" + user +
                '}';
    }
}
