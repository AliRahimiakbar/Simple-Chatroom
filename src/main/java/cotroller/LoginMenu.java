package cotroller;

import model.User;

public class LoginMenu {

    public boolean isUserExist(String username){
        return User.getUserByUsername(username) != null;
    }

    public boolean isPasswordMatch(String username,String password){
        User user=User.getUserByUsername(username);
        return user.getPassword().equals(password);
    }
}
