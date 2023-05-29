package cn.wolfcode.mq;

import cn.wolfcode.domain.OperateIntergralVo;
import cn.wolfcode.domain.OrderInfo;
import cn.wolfcode.service.IOrderInfoService;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
@RocketMQTransactionListener(txProducerGroup = "tx_group")
public class OrderTransactionMessageListener implements RocketMQLocalTransactionListener {
    @Autowired
    private IOrderInfoService iOrderInfoService;
    //本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("执行本地事务方法");
        byte[] contents = (byte[]) message.getPayload();
        String objstr = new String(contents, Charset.defaultCharset());
        OperateIntergralVo vo = JSON.parseObject(objstr, OperateIntergralVo.class);
        System.out.println(vo);
        String OrderNo = (String) o;
        try {
            //本地业务逻辑
            iOrderInfoService.updateRefundStatus(OrderNo);
            System.out.println("执行成功");
//            return RocketMQLocalTransactionState.COMMIT;
            return RocketMQLocalTransactionState.UNKNOWN;
        }catch (Exception e){
            System.out.println("执行失败");
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //回查方法
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        System.out.println("回查方法");
        String orderNo = (String) message.getHeaders().get("orderNo");
        OrderInfo orderInfo = iOrderInfoService.find(orderNo);
        if (OrderInfo.STATUS_REFUND.equals(orderInfo.getStatus())){
            //已经退款成功,说明本地事务执行成功
            System.out.println("返回成功");
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            //说明本地事务执行失败
            System.out.println("返回失败");
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
}
