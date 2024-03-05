package tms.service;

import tms.entity.User;
import tms.storage.JDBCUserStorage;
import tms.storage.UserStorage;

import java.util.Optional;

public class UserService{
    private static UserService instance;
    private final UserStorage storage = JDBCUserStorage.getInstance();
    private UserService(){

    }
    public static UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

    public void save(User user) {
        storage.save(user);
    }

    public Optional<User> findByEmail(String email) {return storage.findByEmail(email);}

    public boolean checkEmail(String email) {
        return storage.checkEmail(email);
    }

    public boolean checkUsername(String username) {
        return storage.checkUsername(username);
    }
}
