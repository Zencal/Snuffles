package net.zencal.snuffles.domain.dubtrack;

public class DubUserInfo {
    protected Integer __v;
    protected String _id;
    protected String locale;
    protected String userid;

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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "DubUserInfo{" +
                "__v=" + __v +
                ", _id='" + _id + '\'' +
                ", locale='" + locale + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
