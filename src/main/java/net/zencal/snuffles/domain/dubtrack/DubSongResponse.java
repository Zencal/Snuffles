package net.zencal.snuffles.domain.dubtrack;

public class DubSongResponse extends DubResponse {
    protected DubSong data;

    public DubSong getData() {
        return data;
    }

    public void setData(DubSong data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DubRoomResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
