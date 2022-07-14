package org.tdsast.joinus.model.request;

import javax.validation.constraints.NotBlank;

public class NewClubRequest {
    @NotBlank
    private String name;

    public NewClubRequest() {
    }

    public NewClubRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
