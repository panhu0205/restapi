package com.example.restfulservice.user.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.restfulservice.user.bean.User;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private Integer userscount = 2;

    static {
        users.add(new User(1, "John" , new Date()));
        users.add(new User(2, "Simon", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId() == null){
            user.setId(++userscount);
        }
        users.add(user);
        return user;
    }

    public User findOne (Integer id){
        for (User user:users){
            if (user.getId() == id){
            return  user;
            }
        }
        return null;
    }

    public User deleteUserbyid (int id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
