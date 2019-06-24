package com.itcast.service.impl;

import com.itcast.dao.RoleDao;
import com.itcast.domain.RoleCopy;
import com.itcast.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleCopy> findByUserid(String userid) throws Exception {
        List<RoleCopy> copies=roleDao.findByUserALLRole(userid);
        for (RoleCopy copy : copies) {
            System.out.println("Hello================================******");
            System.out.println(copy.getId()+copy.getTags()+copy.getRoleName());
        }
        return copies;
    }
}
