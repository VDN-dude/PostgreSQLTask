package tms.service;

import tms.dto.RegUserDTO;
import tms.entity.PageableUser;
import tms.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean save(RegUserDTO regUserDTO);
    Optional<PageableUser> findAll(int offset, int pageSize);
    Optional<User> findById(long id);
    boolean modify(User user, Map<String, String[]> parameterMap);
    void delete(long id);
}
