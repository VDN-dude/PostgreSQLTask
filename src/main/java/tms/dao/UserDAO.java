package tms.dao;

import tms.entity.PageableUser;
import tms.entity.User;

import java.util.Optional;

public interface UserDAO {
    void save(User user);
    Optional<PageableUser> findAll(int offset, int pageSize);
    Optional<User> findById(long id);
    void modify(User user);
    void delete(long id);
}
