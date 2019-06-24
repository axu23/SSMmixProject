package com.itcast.controller;

import com.itcast.domain.EFlag;
import com.itcast.domain.Role;
import com.itcast.domain.UserInfo;
import com.itcast.service.UserService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*/user/findAll.do*/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        List<UserInfo> userInfos = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", userInfos);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) {

        try {
            userService.save(userInfo);
            return "redirect:findAll.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "failer";
        }
    }

    @RequestMapping("/findById.do")
    public ModelAndView findByUserid(String id) {
        System.out.println(id);
        UserInfo userInfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findEmail.do")
    public @ResponseBody
    String checkEamil(@RequestBody String email) {
        System.out.println(email);
        UserInfo userInfo = userService.findEmail(email);
        System.out.println(userInfo);
        String falg = null;
        if (userInfo != null) {
            falg = "exist";
            return falg;
        }
        return falg;
    }


    /*findUserByIdAndAllRole.do?id=${user.id}*/
    @RequestMapping("/findUserByIdAndAllRole.do")
    /*@PreAuthorize("authentication.principal.username =='axu'")*/
    /*@PreAuthorize("hasAuthority('orders')")*/
    /*@PreAuthorize("hasRole('ROLE_product') and hasAuthority('USER_SAVE')")*/
    /*@PreAuthorize("hasAnyRole('ROLE_product','ROLE_Order')")*/
    /*@PreAuthorize("hasAnyAuthority('USER_SAVE','USER_FIND')")*/
    public ModelAndView findUserByIdAllRole(String id, HttpServletRequest request) {
        /*查询到用户信息（包括用户的的角色信息）*/
        UserInfo byId = userService.findById(id);
        request.getSession().setAttribute("byid", byId);
        /*查询用户不拥有的角色集合*/
        List<Role> list = userService.findUser_Role(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", byId);
        mv.addObject("roleList", list);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "ids", required = true) String[] ids,
                                @RequestParam(name = "userId", required = true) String userId,
                                HttpServletRequest request) throws Exception {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("byid");
        List<Role> list = userInfo.getRoles();
        System.out.println("获取到sessionYU中数据" + list.toString());
        userService.addUserRole(ids, userId, list);
        return "redirect:findAll.do";
    }

}
