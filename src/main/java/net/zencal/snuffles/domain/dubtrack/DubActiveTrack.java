package net.zencal.snuffles.domain.dubtrack;

public class DubActiveTrack {
    protected Integer startTime;
    protected DubPlaylistSong song;
    protected DubSong songInfo;

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public DubPlaylistSong getSong() {
        return song;
    }

    public void setSong(DubPlaylistSong song) {
        this.song = song;
    }

    public DubSong getSongInfo() {
        return songInfo;
    }

    public void setSongInfo(DubSong songInfo) {
        this.songInfo = songInfo;
    }

    @Override
    public String toString() {
        return "DubActiveTrack{" +
                "startTime=" + startTime +
                ", song=" + song +
                ", songInfo=" + songInfo +
                '}';
    }
}
