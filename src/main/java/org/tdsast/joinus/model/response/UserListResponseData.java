package org.tdsast.joinus.model.response;

import java.util.List;
import org.tdsast.joinus.model.dto.UserDTO;

public class UserListResponseData {
    private List<UserDTO> list;
    private int total;

    public UserListResponseData(List<UserDTO> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<UserDTO> getList() {
        return list;
    }

    public void setList(List<UserDTO> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
