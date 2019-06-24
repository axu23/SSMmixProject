package com.itcast.service;

import com.itcast.domain.Role;
import com.itcast.domain.RoleCopy;
import com.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id);

    UserInfo findEmail(String email);

    List<Role> findUser_Role(String id);

    void addUserRole(String[] roleid, String userid, List<Role> list);
}
