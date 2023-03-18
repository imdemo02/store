package com.imdemo.user.service;


import com.imdemo.param.PageParam;
import com.imdemo.param.UserCheckParam;
import com.imdemo.param.UserLoginParam;
import com.imdemo.pojo.User;
import com.imdemo.utils.R;

/**
 * @Time: 2022/11/28 14:20
 * @author: imdemo
 * description:
 */
public interface UserService {
    /**
     * 检查账号是否可用业务
     *
     * @param userCheckParam 账号参数  已经校验完毕
     * @return 检查结果 001 004
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     *
     * @param user 参数已经校验 ，但是密码是明文！
     * @return 检查结果 001 004
     */
    R register(User user);

    /**
     * 用户登录业务
     *
     * @param userLoginParam 登录账号参数   已经校验完毕
     * @return 检查结果  001  004
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 后台管理调用,查询全部用户数据
     *
     * @param pageParam
     * @return
     */
    R pageList(PageParam pageParam);

    /**
     * 根据用户id删除数据
     *
     * @param userId
     * @return
     */
    R remove(Integer userId);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    R save(User user);
}
