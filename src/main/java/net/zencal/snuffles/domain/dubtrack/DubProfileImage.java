package net.zencal.snuffles.domain.dubtrack;

import java.util.ArrayList;

public class DubProfileImage {
    protected Long bytes;
    protected String etag;
    protected String format;
    protected Integer height;
    protected String public_id;
    protected String resource_type;
    protected String secure_url;
    protected ArrayList<String> tags;
    protected String type;
    protected String getSecure_url;
    protected Long version;
    protected Integer width;

    public Long getBytes() {
        return bytes;
    }

    public void setBytes(Long bytes) {
        this.bytes = bytes;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String public_id) {
        this.public_id = public_id;
    }

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getSecure_url() {
        return secure_url;
    }

    public void setSecure_url(String secure_url) {
        this.secure_url = secure_url;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGetSecure_url() {
        return getSecure_url;
    }

    public void setGetSecure_url(String getSecure_url) {
        this.getSecure_url = getSecure_url;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "DubProfileImage{" +
                "bytes=" + bytes +
                ", etag='" + etag + '\'' +
                ", format='" + format + '\'' +
                ", height=" + height +
                ", public_id='" + public_id + '\'' +
                ", resource_type='" + resource_type + '\'' +
                ", secure_url='" + secure_url + '\'' +
                ", tags=" + tags +
                ", type='" + type + '\'' +
                ", getSecure_url='" + getSecure_url + '\'' +
                ", version=" + version +
                ", width=" + width +
                '}';
    }
}
