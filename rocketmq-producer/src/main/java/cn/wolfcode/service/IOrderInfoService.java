package cn.wolfcode.service;

import cn.wolfcode.domain.OrderInfo;

/**
 * Created by lanxw
 */
public interface IOrderInfoService {
    String refund(String orderNo);

    void updateRefundStatus(String orderNo);

    OrderInfo find(String orderNo);
}
