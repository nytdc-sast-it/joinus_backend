package com.sastit.joinus.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String siteName;
    private String shortName;
    private Boolean apiClosed = Boolean.FALSE;

    public Configuration() {
    }

    public Configuration(Integer id, String siteName, String shortName, Boolean apiClosed) {
        this.id = id;
        this.siteName = siteName;
        this.shortName = shortName;
        this.apiClosed = apiClosed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Boolean getApiClosed() {
        return apiClosed;
    }

    public void setApiClosed(Boolean apiClosed) {
        this.apiClosed = apiClosed;
    }
}
