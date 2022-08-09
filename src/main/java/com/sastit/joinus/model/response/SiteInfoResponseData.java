package com.sastit.joinus.model.response;

import java.io.Serializable;

public class SiteInfoResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean installed;
    private String siteName;
    private String shortName;

    public SiteInfoResponseData() {
    }

    public SiteInfoResponseData(boolean installed, String siteName, String shortName) {
        this.installed = installed;
        this.siteName = siteName;
        this.shortName = shortName;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
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
}
