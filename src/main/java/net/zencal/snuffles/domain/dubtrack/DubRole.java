package net.zencal.snuffles.domain.dubtrack;

import java.util.List;

public class DubRole {
    protected String _id;
    protected String type;
    protected String label;
    protected List<String> rights;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "DubRole{" +
                "_id='" + _id + '\'' +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", rights=" + rights +
                '}';
    }
}
