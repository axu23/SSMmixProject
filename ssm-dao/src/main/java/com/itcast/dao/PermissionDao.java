package com.itcast.dao;

import com.itcast.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface   PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
     List<Permission> findByid(String id) throws Exception;
}
