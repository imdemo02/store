package com.imdemo.admin.service;

import com.imdemo.param.CartListParam;
import com.imdemo.param.PageParam;
import com.imdemo.pojo.User;
import com.imdemo.utils.R;

/**
 * @Time: 2022/12/4 11:19
 * @author: imdemo
 * description:
 */
public interface UserService {

    /**
     * 用户的展示业务方法
     *
     * @param pageParam
     * @return
     */
    R userList(PageParam pageParam);

    /**
     * 删除用户数据
     *
     * @param cartListParam
     * @return
     */
    R userRemove(CartListParam cartListParam);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    R userUpdate(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    R userSave(User user);
}
