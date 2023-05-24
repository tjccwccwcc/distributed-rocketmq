package cn.wolfcode.mq;

import cn.wolfcode.domain.OperateIntergralVo;
import cn.wolfcode.service.IIntegralService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wolfcode-lanxw
 */
@Component
@RocketMQMessageListener(consumerGroup = "consumer-order",topic = "tx_topic")
public class OrderInfoListener implements RocketMQListener<OperateIntergralVo> {
    @Autowired
    private IIntegralService iIntegralService;
    @Override
    public void onMessage(OperateIntergralVo message) {
        System.out.println("消费消息");
        iIntegralService.incrIntergral(message);
    }
}
