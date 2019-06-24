package com.itcast.service;

import com.itcast.domain.Role;
import com.itcast.domain.RoleCopy;

import java.util.List;

public interface RoleService {
    List<RoleCopy> findByUserid(String userid) throws Exception;
}
