package org.tdsast.joinus.model.response;

import org.tdsast.joinus.model.dto.UserDTO;

import java.io.Serializable;
import java.util.List;

public class UserListResponseData extends ResponseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<UserDTO> list;

    public UserListResponseData(List<UserDTO> list) {
        this.list = list;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }
}
