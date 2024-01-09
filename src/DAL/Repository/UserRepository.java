package DAL.Repository;

import DAL.Database.Connect;
import DAL.Model.User;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

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
    public boolean insert(User user) {

		String username = user.getUsername();
		String password = user.getPassword();
		String fullName = user.getFullname();
		int age = user.getAge();


		String sql2 = "INSERT INTO users (username,password, fullname, age, score) VALUES (?,?,?,?,0)";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1,username);
			ps.setString(2,password);
			ps.setString(3, fullName);
			ps.setInt(4,age);

			int record = ps.executeUpdate();
			return record > 0;
		} catch (Exception e1){
			e1.printStackTrace();
			return false;
		}
	}

    public void increaseScore(String username) {
        String sql = "UPDATE users SET score = score + 1 WHERE username = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			int record = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

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
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setFullname(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setScore(rs.getInt(5));
            } else {
				return null;
			}
			return user;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //TODO: find user by username
		cn.closeResources();
        return new User();
    }
	public ArrayList<User> getListUser(){
		ArrayList<User> ListUser = new ArrayList<User>();

		String sql = "Select * from users order by score DESC";
		rs = cn.listAll(sql);
		try {
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setFullname(rs.getString(3));
				user.setAge(rs.getInt(4));
				user.setScore(rs.getInt(5));
				ListUser.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cn.closeResources();
		return ListUser;
	}

	public boolean Login(String Username, String Password) throws SQLException, UnknownHostException, IOException {
		rs=cn.listAll("Select * from users where username = '"+Username+"' and password = '"+Password+"'");
		if(rs.next()) {
			return true;
		}else {
//			JOptionPane.showMessageDialog(null, "Username or Password incorrect");
			return false;
		}
	}
}
