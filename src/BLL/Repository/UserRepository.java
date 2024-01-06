package BLL.Repository;

import Model.User;
import Data.Database.Connect;

import java.sql.*;

public class UserRepository {

//    public User insert(User user) {
//
//    }

    Connect cn = new Connect();

	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;

	public UserRepository() {
		conn = new Connect().connect();
	}
    public boolean Insert(String username, String password, String fullname, int age) {

		String sql2 = "INSERT INTO users (username,password, fullname, age, score) VALUES (?,?,?,?,0)";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1,username);
			ps.setString(2,password);
			ps.setString(3, fullname);
			ps.setInt(4,age);

			int record = ps.executeUpdate();
			return record > 0;
		}catch (Exception e1){
			e1.printStackTrace();
			return false;
		}
	}

    public void update(String username) {
        String sql = "UPDATE users SET score = score + 1 WHERE usename = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			int record = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

		}
    }

    public int getScore(String username) {
		String sql = "SELECT score FROM users WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

    public void delete() {

    }

    public void find(int id) {

    }

    public User findByUsername(String username) {
        User user = new User();
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
				user.setUsername(rs.getString(0));
				user.setFullname(rs.getString(2));
				user.setAge(rs.getInt(3));
				user.setScore(rs.getInt(4));
            }
			return user;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //TODO: find user by username
        return new User();
    }
}
