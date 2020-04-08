package com.qiangzeng.learning.ucservice.controller;


import com.qiangzeng.learning.common.util.JwtUtils;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.ucservice.entity.UcenterMember;
import com.qiangzeng.learning.ucservice.entity.vo.RegisterVo;
import com.qiangzeng.learning.ucservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author qiangzeng
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/ucservice/ucenter-member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public ResponseResult loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return ResponseResult.success(token);
    }

    //注册
    @PostMapping("register")
    public ResponseResult registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return ResponseResult.success();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public ResponseResult getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return ResponseResult.success(member);
    }

    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public ResponseResult countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return ResponseResult.success(count);
    }

}

