package com.nbclass.controller;

import com.nbclass.exception.ZbException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version V1.0
 * @date 2018年7月13日
 * @author superzheng
 */
@Controller
public class RenderController {

    /*首页*/
    @RequestMapping(value={"/","/index"})
    public String index(){
        return "index/index";
    }

    /*工作台*/
    @GetMapping("/workdest")
    public String workdest(){
        return "index/workdest";
    }

    /**用户列表入口*/
    @RequiresPermissions("users")
    @GetMapping("/users")
    public String userList(){
        return "user/list";
    }

    /*角色列表入口*/
    @RequiresPermissions("roles")
    @GetMapping("/roles")
    public String roleList() {
        return "role/list";
    }

    /*权限列表入口*/
    @RequiresPermissions("permissions")
    @GetMapping("/permissions")
    public String permissionList(){
        return "permission/list";
    }
}
