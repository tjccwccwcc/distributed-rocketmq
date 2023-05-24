package cn.wolfcode.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * Created by lanxw
 */
@Service
public interface UsableIntegralMapper {
    void addIntergral(@Param("userId") Long userId, @Param("amount") Long amount);
}
