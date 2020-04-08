package com.qiangzeng.learning.ucservice.service;

import com.qiangzeng.learning.ucservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiangzeng.learning.ucservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author qiangzeng
 * @since 2020-04-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);

    //根据openid判断
    UcenterMember getOpenIdMember(String openid);

    //查询某一天注册人数
    Integer countRegisterDay(String day);

}
