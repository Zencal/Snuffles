package net.zencal.snuffles.domain.dubtrack;

import java.util.List;

public class DubUserListResponse extends DubResponse {
    protected List<DubCurrentUser> data;

    public List<DubCurrentUser> getData() {
        return data;
    }

    public void setData(List<DubCurrentUser> data) {
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

