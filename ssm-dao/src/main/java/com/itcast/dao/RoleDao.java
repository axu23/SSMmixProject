package com.itcast.dao;

import com.itcast.domain.Role;
import com.itcast.domain.RoleCopy;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    /*根据用户id查询出角色*/
    @Select("select * from role where id in(select roleid from users_role where userid=#{userid})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itcast.dao.PermissionDao.findByid"))
    })
    List<Role> findById(String userid) throws Exception;

    @Select("select r.*,nvl2(ur.userid,'1','0') tags from role r left join (select * from users_role where userid = #{userid}) ur on r.id = ur.roleid")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "tags", column = "tags")
    })
    List<RoleCopy> findByUserALLRole(String userid) throws Exception;

}
