package net.zencal.snuffles.domain.dubtrack.response;

import net.zencal.snuffles.domain.dubtrack.DubRoomUser;

import java.util.List;

public class DubUserListResponse extends DubResponse {
    protected List<DubRoomUser> data;

    public List<DubRoomUser> getData() {
        return data;
    }

    public void setData(List<DubRoomUser> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DubUserListResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

