package com.sastit.joinus.model.response;

import com.sastit.joinus.model.dto.ClubDTO;

import java.io.Serializable;
import java.util.List;

public class ClubListResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ClubDTO> list;

    public ClubListResponseData(List<ClubDTO> list) {
        this.list = list;
    }

    public List<ClubDTO> getList() {
        return list;
    }

    public void setList(List<ClubDTO> list) {
        this.list = list;
    }
}
