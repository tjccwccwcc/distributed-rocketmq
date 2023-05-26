package cn.wolfcode.service.impl;

import cn.wolfcode.domain.OperateIntergralVo;
import cn.wolfcode.domain.OrderInfo;
import cn.wolfcode.mapper.OrderInfoMapper;
import cn.wolfcode.service.IOrderInfoService;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lanxw
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Override
    @Transactional
    public String refund(String orderNo){
        System.out.println("发送消息");
        OrderInfo orderInfo = orderInfoMapper.select(orderNo);
        OperateIntergralVo vo = new OperateIntergralVo();
        vo.setUserId(orderInfo.getUserId());
        vo.setValue(orderInfo.getIntergral());
        Message<OperateIntergralVo> message =
                MessageBuilder.withPayload(vo).setHeader("orderNo", orderNo).build();
        //发送事务消息
        TransactionSendResult result = rocketMQTemplate.sendMessageInTransaction(
                "tx_group",
                "tx_topic",
                message,
                orderNo
        );
        System.out.println("发送的状态：" + result.getSendStatus());
        System.out.println("本地事务的执行结果：" + result.getLocalTransactionState());
        if (LocalTransactionState.COMMIT_MESSAGE.equals(result.getLocalTransactionState())){
            return "退款成功";
        }else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "退款失败";
        }
    }

    @Override
    @Transactional
    public void updateRefundStatus(String orderNo) {
        orderInfoMapper.changeRefundStatus(orderNo, OrderInfo.STATUS_REFUND);
        int i = 1/0;
    }

    @Override
    public OrderInfo find(String orderNo) {
        return orderInfoMapper.select(orderNo);
    }
}
