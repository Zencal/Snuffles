package net.zencal.snuffles.domain.dubtrack;

import java.util.List;

public class DubHistoryResponse extends DubResponse {
    protected List<DubHistory> data;

    public List<DubHistory> getData() {
        return data;
    }

    public void setData(List<DubHistory> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DubHistoryResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
