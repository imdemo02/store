package com.imdemo.user.controller;

import com.imdemo.param.AddressListParam;
import com.imdemo.param.AddressParam;
import com.imdemo.param.AddressRemoveParam;
import com.imdemo.pojo.Address;
import com.imdemo.user.service.AddressService;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Time: 2022/11/28 18:40
 * @author: imdemo
 * description: 地址模块的控制器
 */
@RestController
@RequestMapping("user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("参数异常,查询失败!");
        }
        return addressService.list(addressListParam.getUserId());
    }

    @PostMapping("save")
    public R save(@RequestBody @Validated AddressParam addressParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("参数异常,保存失败!");
        }

        Address address = addressParam.getAdd();
        address.setUserId(addressParam.getUserId());

        return addressService.save(address);
    }

    @PostMapping("remove")
    public R remove(@RequestBody @Validated AddressRemoveParam addressRemoveParam, BindingResult result) {
        if (result.hasErrors()) {

            return R.fail("参数异常,删除失败!");
        }
        return addressService.remove(addressRemoveParam.getId());
    }
}
