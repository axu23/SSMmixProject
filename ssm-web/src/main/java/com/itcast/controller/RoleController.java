package com.itcast.controller;

import com.itcast.domain.RoleCopy;
import com.itcast.service.RoleService;
import com.itcast.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*findUserByIdAndAllRole.do?id=${user.id}*/
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAllRole(String id, HttpServletRequest request) throws Exception {
        /*查询到用户全部角色信息（包括的flag=1不包括flag=0）信息（包括用户的的角色信息）*/
        List<RoleCopy> byId = roleService.findByUserid(id);
        System.out.println(byId);
        request.getSession().setAttribute("byid", byId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("userid",id);
        mv.addObject("roleList", byId);
        mv.setViewName("user-role-add2");
        return mv;
    }

}
