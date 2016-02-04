package net.zencal.snuffles.domain.dubtrack.payload;

import net.zencal.snuffles.domain.dubtrack.DubPlaylistSong;
import net.zencal.snuffles.domain.dubtrack.DubSong;

public class DubPlaylistUpdatePayload {
    protected DubPlaylistSong song;
    protected String type;
    protected Long startTime;
    protected DubSong songInfo;

    public DubPlaylistSong getSong() {
        return song;
    }

    public void setSong(DubPlaylistSong song) {
        this.song = song;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public DubSong getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(DubSong songInfo) {
        this.songInfo = songInfo;
    }

    @Override
    public String toString() {
        return "DubPlaylistUpdatePayload{" +
                "song=" + song +
                ", type='" + type + '\'' +
                ", startTime=" + startTime +
                ", songInfo=" + songInfo +
                '}';
    }
}
