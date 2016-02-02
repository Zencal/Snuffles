package net.zencal.snuffles.domain.dubtrack;

public class DubRoomResponse extends DubResponse {
    protected DubRoom data;

    public DubRoom getData() {
        return data;
    }

    public void setData(DubRoom data) {
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
