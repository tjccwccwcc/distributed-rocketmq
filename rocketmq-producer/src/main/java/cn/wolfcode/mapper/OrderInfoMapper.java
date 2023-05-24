package cn.wolfcode.mapper;

import cn.wolfcode.domain.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by wolfcode-lanxw
 */
@Service
public interface OrderInfoMapper {
    OrderInfo select(String orderNo);
    int changeRefundStatus(@Param("orderNo") String orderNo,@Param("status") int status);
}
