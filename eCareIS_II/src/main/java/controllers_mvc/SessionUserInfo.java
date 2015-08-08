package controllers_mvc;


import java.io.Serializable;

public class SessionUserInfo implements Serializable {
    private static final long serialVersionUID = 1355122041951251207L;
    private Integer role;
    private Integer contracId;
    private String name;

    public SessionUserInfo(Integer role, Integer contracId, String name) {
        this.role = role;
        this.contracId = contracId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setContracId(Integer contracId) {
        this.contracId = contracId;
    }

    public Integer getContracId() {
        return contracId;
    }
}
