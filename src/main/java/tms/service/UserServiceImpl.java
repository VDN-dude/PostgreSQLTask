package tms.service;

import tms.dto.RegUserDTO;
import tms.entity.PageableUser;
import tms.entity.User;
import tms.mapper.UserMapper;
import tms.dao.JDBCUserDAO;
import tms.dao.UserDAO;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;


public class UserServiceImpl implements UserService{
    private final UserDAO userDAO = JDBCUserDAO.getInstance();
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private static UserServiceImpl instance;
    private UserServiceImpl(){

    }
    public static UserServiceImpl getInstance(){
        if (instance == null){
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void save(RegUserDTO regUserDTO) {
        System.out.println(regUserDTO);
        User user = userMapper.regUserDtoToUser(regUserDTO);
        System.out.println(user);
        userDAO.save(user);
    }

    @Override
    public Optional<PageableUser> findAll(int offset, int pageSize) {
        return userDAO.findAll(offset, pageSize);
    }

    @Override
    public Optional<User> findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    public boolean modify(User user, Map<String, String[]> parameterMap) {
        Iterator<String> iterator = parameterMap.keySet().iterator();
        boolean changed = false;
        while (iterator.hasNext()) {
            String param = iterator.next();
            switch (param) {
                case "firstname":
                    String firstname = parameterMap.get("firstname")[0];
                    if (!user.getFirstname().equals(firstname)) {
                        user.setFirstname(firstname);
                        changed = true;
                    }
                    break;
                case "lastname":
                    String lastname = parameterMap.get("lastname")[0];
                    if (!user.getLastname().equals(lastname)) {
                        user.setLastname(lastname);
                        changed = true;
                    }
                    break;
                case "age":
                    int age = Integer.parseInt(parameterMap.get("age")[0]);
                    if (user.getAge() != age) {
                        user.setAge(age);
                        changed = true;
                    }
                    break;
                case "phoneNumber":
                    String phoneNumber = parameterMap.get("phoneNumber")[0];
                    if (!user.getPhoneNumber().equals(phoneNumber)) {
                        user.setPhoneNumber(phoneNumber);
                        changed = true;
                    }
                    break;
            }
        }

        if (changed){
            userDAO.modify(user);
        }
        return changed;
    }

    @Override
    public void delete(long id) {
        userDAO.delete(id);
    }
}
