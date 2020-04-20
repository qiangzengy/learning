package com.qiangzeng.learning.order.service;

import com.qiangzeng.learning.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author qiangzeng
 * @since 2020-04-20
 */
public interface TOrderService extends IService<TOrder> {
    //1 生成订单的方法
    String createOrders(String courseId, String memberIdByJwtToken);

}
