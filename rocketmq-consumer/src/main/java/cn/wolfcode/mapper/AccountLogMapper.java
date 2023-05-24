package cn.wolfcode.mapper;

import cn.wolfcode.domain.AccountLog;

/**
 * Created by lanxw
 */
public interface AccountLogMapper {
    /**
     * 插入日志
     * @param accountLog
     */
    void insert(AccountLog accountLog);
}
