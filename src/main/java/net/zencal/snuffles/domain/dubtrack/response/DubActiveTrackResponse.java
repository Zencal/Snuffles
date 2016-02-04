package net.zencal.snuffles.domain.dubtrack.response;

import net.zencal.snuffles.domain.dubtrack.DubActiveTrack;

public class DubActiveTrackResponse extends DubResponse {
    protected DubActiveTrack data;

    public DubActiveTrack getData() {
        return data;
    }

    public void setData(DubActiveTrack data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DubActiveTrackResponse{" +
                "data=" + data +
                '}';
    }
}
