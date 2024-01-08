package util;

import DAL.Model.User;

import java.util.List;
import java.util.Vector;

public class VectorSupport {
    public static Vector ArraylistToVector(List<User> arrayListUser) {
        Vector vD = new Vector<>();
        for (User user : arrayListUser) {
            System.out.println(user);
            Vector vtemp = new Vector<>();
            vtemp.add(user.getUsername());
            vtemp.add(user.getFullname());
            vtemp.add(user.getAge());
            vtemp.add(user.getScore());
            vD.add(vtemp);
        }
        return vD;
    }
}
