package util;

import Model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class VectorSubport {
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
