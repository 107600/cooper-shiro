package com.nbclass.service.impl;

import com.nbclass.mapper.UserMapper;
import com.nbclass.mapper.UserRoleMapper;
import com.nbclass.model.User;
import com.nbclass.model.UserRole;
import com.nbclass.service.UserService;
import com.nbclass.util.ResultUtil;
import com.nbclass.vo.base.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public int register(User user) {
        int a = userMapper.insert(user);
        return a;
    }

    @Override
    public void updateLastLoginTime(User user) {
        userMapper.updateLastLoginTime(user);
    }

    @Override
    public List<User> selectUsers(User user) {
        return userMapper.selectUsers(user);
    }

    @Override
    public User selectByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }

    @Override
    public int updateByUserId(User user) {
        return userMapper.updateByUserId(user);
    }

    @Override
    public int updateStatusBatch(List<String> userIds,Integer status) {
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("userIds",userIds);
        params.put("status",status);
        return userMapper.updateStatusBatch(params);
    }

    @Override
    public ResponseVo addAssignRole(String userId, List<String> roleIds) {
        try{
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRoleMapper.delete(userRole);
            for(String roleId :roleIds){
                userRole.setId(null);
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            }
            return ResultUtil.success("分配角色成功");
        }catch(Exception e){
            return ResultUtil.error("分配角色失败");
        }
    }

    @Override
    public int updateUserByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }


}
