package cn.wolfcode.service.impl;

import cn.wolfcode.domain.AccountLog;
import cn.wolfcode.domain.OperateIntergralVo;
import cn.wolfcode.mapper.UsableIntegralMapper;
import cn.wolfcode.service.IIntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lanxw
 */
@Service
public class IntegralServiceImpl implements IIntegralService {
    @Autowired
    private UsableIntegralMapper usableIntegralMapper;
    @Override
    @Transactional
    public void incrIntergral(OperateIntergralVo operateIntergralVo) {
        usableIntegralMapper.addIntergral(operateIntergralVo.getUserId(),operateIntergralVo.getValue());
    }
}
