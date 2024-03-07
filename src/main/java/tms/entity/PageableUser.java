package tms.entity;

import java.util.List;

public class PageableUser {
    public List<User> userList;
    public long countOfPages;
    public int size;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public long getCountOfPages() {
        return countOfPages;
    }

    public void setCountOfPages(long countOfPages) {
        this.countOfPages = countOfPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
