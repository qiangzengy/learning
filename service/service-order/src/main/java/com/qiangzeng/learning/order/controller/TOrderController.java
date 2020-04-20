package com.qiangzeng.learning.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiangzeng.learning.common.util.JwtUtils;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.order.entity.TOrder;
import com.qiangzeng.learning.order.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author qiangzeng
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/eduorder/order")
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    //1 生成订单的方法
    @PostMapping("createOrder/{courseId}")
    public ResponseResult saveOrder(@PathVariable String courseId, HttpServletRequest request) {
        //创建订单，返回订单号
        String orderNo =
                orderService.createOrders(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return ResponseResult.success(orderNo);
    }

    //2 根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public ResponseResult getOrderInfo(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(wrapper);
        return ResponseResult.success(order);
    }

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        if(count>0) { //已经支付
            return true;
        } else {
            return false;
        }
    }

}

