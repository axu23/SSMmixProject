package com.itcast.dao;

import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itcast.dao.RoleDao.findById"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll()throws Exception;

    @Insert("insert into users(email,username,password,phonenum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itcast.dao.RoleDao.findById")),
    })
    UserInfo findById(String id);

    @Select("select * from users where email=#{email}")
    UserInfo findEmail(String email);

    @Select("select * from users ")
    List<Role> findnotRole(String id);

    @Select("select role.* from role where not exists (select roleid from users_role where role.id=roleid and userid=#{id})")
    List<Role> findAuRole(String id);

    @Insert("insert into users_role values (#{userid},#{roleid})")
    void addRole(@Param(value = "userid") String userid,@Param(value = "roleid") String roleid);

    @Delete("delete from users_role where userid=#{userid} and roleid=#{id}")
    void deleteByid(@Param(value = "id") String id, @Param(value = "userid") String userid);
}
