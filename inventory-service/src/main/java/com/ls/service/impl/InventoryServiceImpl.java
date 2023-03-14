package com.ls.service.impl;

import com.ls.entity.Inventory;
import com.ls.exception.BusinessException;
import com.ls.exception.ErrorEnum;
import com.ls.mapper.InventoryMapper;
import com.ls.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-17
 */
@DubboService
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @Resource
    private InventoryMapper inventoryMapper;
    @Override
    public void subInventoryNumByProductId(Map<Long, Long> productIdAndNum) {
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            InventoryMapper mapper = sqlSession.getMapper(InventoryMapper.class);
            int i=0;
            for (Map.Entry<Long, Long> entry : productIdAndNum.entrySet()) {
                mapper.subInventoryNumByProductId(entry.getKey(), entry.getValue());
                //每 50 条提交一次
                if((i + 1) % 50 == 0){
                    sqlSession.flushStatements();
                }
                i++;
            }
            sqlSession.flushStatements();
        } catch (Exception e) {
            throw new BusinessException(ErrorEnum.E_20013);
        }
    }
}
