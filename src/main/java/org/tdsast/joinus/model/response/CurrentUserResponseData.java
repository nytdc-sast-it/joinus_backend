package org.tdsast.joinus.model.response;

import java.io.Serializable;

public class CurrentUserResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private Boolean isAdmin;
    private String avatar = "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png";

    public CurrentUserResponseData(Long id, String username, Boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
