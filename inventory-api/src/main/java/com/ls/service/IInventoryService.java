package com.ls.service;

import com.ls.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-17
 */
public interface IInventoryService extends IService<Inventory> {
    void subInventoryNumByProductId(Map<Long, Long> productIdAndNum);
}
