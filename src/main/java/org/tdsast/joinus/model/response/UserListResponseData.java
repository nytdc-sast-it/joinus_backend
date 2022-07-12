package org.tdsast.joinus.model.response;

import java.io.Serializable;
import java.util.List;
import org.tdsast.joinus.model.dto.UserDTO;

public class UserListResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<UserDTO> list;
    private long total;

    public UserListResponseData(List<UserDTO> list, long total) {
        this.list = list;
        this.total = total;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}