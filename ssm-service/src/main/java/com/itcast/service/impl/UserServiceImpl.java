package com.itcast.service.impl;

import com.itcast.PasswordUtils;
import com.itcast.dao.UserDao;
import com.itcast.domain.Permission;
import com.itcast.domain.Role;
import com.itcast.domain.RoleCopy;
import com.itcast.domain.UserInfo;
import com.itcast.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(s);
            logger.info(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (userInfo == null) {
            return null;
        }
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), getAuthorty(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthorty(List<Role> list) {
        List<SimpleGrantedAuthority> list1 = new ArrayList<>();
        for (Role role : list) {
            /*遍历用户角色并添加到list中*/
            list1.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            System.out.println("user Has Role +=+ "+role.getRoleName());
            /*遍历角色权限并添加到list中*/
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                list1.add(new SimpleGrantedAuthority(permission.getPermissionName()));
                System.out.println("user Has Permission ===" + permission.getPermissionName());
            }
        }
        return list1;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        List<UserInfo> list = userDao.findAll();
        return list;
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        String password = PasswordUtils.getPassword(userInfo.getPassword());
        userInfo.setPassword(password);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        return userInfo;
    }

    @Override
    public UserInfo findEmail(String email) {
        UserInfo userInfo = userDao.findEmail(email);
        return userInfo;
    }

    @Override
    public List<Role> findUser_Role(String id) {
        List<Role> list = userDao.findAuRole(id);
        return list;
    }

    @Override
    public void addUserRole(String[] roleid, String userid, List<Role> beforeRole) {
        /*roleid数组为用户改变之后的集合*/
        /*list集合为用改变之前的集合*/
        for (String s : roleid) {
            System.out.println(s);
        }

        //将roleid字符串数组转换为List集合
        ArrayList<String> afterRole = new ArrayList<>();
        afterRole.addAll(Arrays.asList(roleid));

        /*下面开始分情况判断两个集合*/

        /*第一种afterRole集合为空（用户删除了全部角色）*/
        if (afterRole.size() == 0) {
            /*下面就遍历list集合（用户将角色全部删除掉）*/
            if (beforeRole.size() != 0) {
                for (Role role : beforeRole) {
                    userDao.deleteByid(role.getId(), userid);
                }
            }
        }
        /*第一种情况list集合为空，而afterRole集合不为空（用户新增了角色）*/
        if (beforeRole.size() == 0) {
            if (afterRole.size() != 0) {
                for (String string : afterRole) {
                    userDao.addRole(userid, string);
                }
            }
        }


        /*第三种情况，afterRole集合有部分增加或者减少*/
        /*将所有的角色id放置到集合中*/
        ArrayList<String> roled = new ArrayList<String>();
        for (Role role : beforeRole) {
            roled.add(role.getId());
        }

        /*判断afterRole集合中数据是否存在于list集合中*/
        for (String string : afterRole) {
            if (!roled.contains(string)) {
                userDao.addRole(userid, string);
            }
        }

        /*判断list集合中数据是否存在于afterRole集合中*/
        for (Role role : beforeRole) {
            if (!afterRole.contains(role.getId())) {
                userDao.deleteByid(role.getId(), userid);
            }
        }
    }
}
