package net.zencal.snuffles.domain.dubtrack;

public class DubUserResponse extends DubResponse{
    protected DubUser data;

    public DubUser getData() {
        return data;
    }

    public void setData(DubUser data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DubUserResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
