package com.imdemo.admin.service;

import com.imdemo.admin.param.AdminUserParam;
import com.imdemo.admin.pojo.AdminUser;
import com.imdemo.utils.R;

/**
 * @Time: 2022/12/3 19:29
 * @author: imdemo
 * description:
 */
public interface AdminUserService {

    /**
     * 登录业务方法
     *
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
