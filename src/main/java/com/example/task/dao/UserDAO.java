package com.example.task.dao;

import com.example.task.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void createUser(User user) {
        String query="INSERT INTO user_profile(userName,password,address,companyId,phone) VALUES(?,?,?,?,?)";
         jdbcTemplate.update(query,user.getUserName(),user.getPassword(),user.getAddress(),user.getCompanyId(),user.getPhone());
    }
    public void deleteUser(String userName,User user) {
        String query="DELETE  from user_profile  WHERE userName = ?";
        jdbcTemplate.update(query,userName);
    }

    public User getUser(String userName,String password) {
        String query="SELECT * FROM user_profile WHERE userName = ? AND password = ?";
        User user=null;
        try{
             user = jdbcTemplate.queryForObject(query, new Object[]{userName , password}, new RowMapper<User>(){
                @Override
                public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    User user1 =new User();
                    user1.setId(rs.getInt("id"));
                    user1.setUserName(rs.getString("userName"));
                    user1.setAddress(rs.getString("address"));
                    user1.setCompanyId(rs.getString("companyId"));
                    user1.setPhone(rs.getString("phone"));
                    return user1;
                }
            });
        }catch (Exception e){}

    return  user;
    }

    public User getUser(String userName) {
        String query="SELECT * FROM user_profile WHERE userName = ?";
        User user=null;
        try {
             user = jdbcTemplate.queryForObject(query, new Object[]{userName}, new RowMapper<User>(){
                @Override
                public User mapRow(ResultSet rs, int rwNumber) throws SQLException {
                    User user1 =new User();
                    user1.setId(rs.getInt("id"));
                    user1.setUserName(rs.getString("userName"));
                    user1.setPassword("****");
                    user1.setAddress(rs.getString("address"));
                    user1.setCompanyId(rs.getString("companyId"));
                    user1.setPhone(rs.getString("phone"));
                    return user1;
                }
            });
        }catch (Exception e){}

        return  user;
    }

    public boolean login(String userName , String password,User user){
        user=getUser(userName,password);
        return user != null;
    }

    public Object updateUser(String username,User user) {
        int update = 0;
        String query="UPDATE user_profile SET userName=? ,password=? , address=? , companyId=? , phone=? WHERE userName=?";
        if (getUser(user.getUserName()) == null) {
            update = jdbcTemplate.update(query, user.getUserName(), user.getPassword(), user.getAddress(), user.getCompanyId(), user.getPhone(), username);
        return user;

        }
        else
        {
            return "This username already exist please choose another one";
        }

    }

}