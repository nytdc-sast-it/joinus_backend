package org.tdsast.joinus.model.request;

public class InstallRequest {
    private String siteName;
    private String shortName;
    private String admin;
    private String password;

    public InstallRequest() {
    }

    public InstallRequest(String siteName, String shortName, String admin, String password) {
        this.siteName = siteName;
        this.shortName = shortName;
        this.admin = admin;
        this.password = password;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
