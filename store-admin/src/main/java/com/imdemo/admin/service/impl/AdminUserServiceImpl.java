package com.imdemo.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imdemo.admin.mapper.AdminUserMapper;
import com.imdemo.admin.param.AdminUserParam;
import com.imdemo.admin.pojo.AdminUser;
import com.imdemo.admin.service.AdminUserService;
import com.imdemo.constants.UserConstants;
import com.imdemo.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Time: 2022/12/3 19:39
 * @author: imdemo
 * description: 实现类
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 登录业务方法
     *
     * @param adminUserParam
     * @return
     */
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", adminUserParam.getUserAccount());
        //密码经过MD5加密和加盐 之后  做比对
        queryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserPassword() + UserConstants.USER_SLAT));

        AdminUser user = adminUserMapper.selectOne(queryWrapper);
        log.info("AdminUserServiceImpl.login业务结束,结果:{}", user);
        return user;
    }
}
