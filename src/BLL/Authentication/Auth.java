package BLL.Authentication;

import DAL.Model.User;

public class Auth {
    private static volatile Auth instance;
    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }

        return instance;
    }

    User user;

    public User getCurrentUser() {
      return user;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }
}
