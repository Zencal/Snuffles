package net.zencal.snuffles.domain.dubtrack;

public class DubUser {
    protected String username;
    protected Integer status;
    protected Integer roleid;
    protected Integer lastLogin;
    protected Integer dubs;
    protected Long created;
    protected Integer __v;
    protected String _id;
    protected DubProfileImage profileImage;
    protected DubUserInfo userInfo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Integer lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getDubs() {
        return dubs;
    }

    public void setDubs(Integer dubs) {
        this.dubs = dubs;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Integer get__v() {
        return __v;
    }

    public void set__v(Integer __v) {
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public DubProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(DubProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public DubUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(DubUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "DubUser{" +
                "username='" + username + '\'' +
                ", status=" + status +
                ", roleid=" + roleid +
                ", lastLogin=" + lastLogin +
                ", dubs=" + dubs +
                ", created=" + created +
                ", __v=" + __v +
                ", _id='" + _id + '\'' +
                ", profileImage=" + profileImage +
                ", userInfo=" + userInfo +
                '}';
    }
}
