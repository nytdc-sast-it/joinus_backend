package org.tdsast.joinus.model.response;

import java.io.Serializable;
import java.util.List;
import org.tdsast.joinus.model.dto.ClubDTO;

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
