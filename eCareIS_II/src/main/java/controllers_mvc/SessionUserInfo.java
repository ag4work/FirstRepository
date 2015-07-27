package controllers_mvc;


public class SessionUserInfo {
    private Integer role;
    private Integer contracId;

    public SessionUserInfo(Integer role, Integer contracId) {
        this.role = role;
        this.contracId = contracId;
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
