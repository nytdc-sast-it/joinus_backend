package com.sastit.joinus.model.response;

import com.sastit.joinus.model.dto.ClubDTO;

import java.io.Serializable;

public class ClubResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private ClubDTO club;

    public ClubResponseData(ClubDTO club) {
        this.club = club;
    }

    public ClubDTO getClub() {
        return club;
    }

    public void setClub(ClubDTO club) {
        this.club = club;
    }
}
