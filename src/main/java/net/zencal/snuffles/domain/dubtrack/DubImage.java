package net.zencal.snuffles.domain.dubtrack;

public class DubImage {
    protected String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "DubImage{" +
                "thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
