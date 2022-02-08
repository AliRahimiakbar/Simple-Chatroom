package cotroller;

import model.User;

public class RegisterMenu {

    public void register(String fullName,String username,String password){
        new User(fullName, username,password);
    }

    public boolean isUserExist(String username){
        return User.getUserByUsername(username) != null;
    }

    public boolean isPasswordsEqual(String password,String password2){
        return password.equals(password2);
    }



}
