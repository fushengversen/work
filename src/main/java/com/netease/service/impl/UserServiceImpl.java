package com.netease.service.impl;

import com.netease.mapper.UserDao;
import com.netease.mapper.UserMapper;
import com.netease.pojo.User;
import com.netease.pojo.UserExample;

import com.netease.service.UserService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
//    private UserMapper userMapper;
    public UserDao userDao;
    @Override
    public User userLogin(String username, String password) {

        User user = userDao.getUser(username, password);
        if(null != user) System.out.println(user.getUsername());
        else System.out.println("null user");
        return user;
//        UserExample example = new UserExample();
//        UserExample.Criteria criteria = example.createCriteria();
//        System.out.println(username+" and  "+password+" "+ criteria);
//        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);
//        List<User> list = userMapper.selectByExample(example);
//        userMapper.countByExample(example);
//        if (null == list || list.size() == 0) {
//            return null;
//        }
//
//        return list.get(0);
//            User user = new User();
//            user.setUsername("zhangsan");
//            user.setPassword("123");
//            return user;
    }
}
