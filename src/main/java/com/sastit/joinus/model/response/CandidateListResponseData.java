package com.sastit.joinus.model.response;

import com.sastit.joinus.model.dto.CandidateDTO;

import java.io.Serializable;
import java.util.List;

public class CandidateListResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CandidateDTO> list;
    private long total;

    public CandidateListResponseData(List<CandidateDTO> list, long total) {
        this.list = list;
        this.total = total;
    }

    public List<CandidateDTO> getList() {
        return list;
    }

    public void setList(List<CandidateDTO> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
