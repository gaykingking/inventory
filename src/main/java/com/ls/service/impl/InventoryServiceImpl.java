package com.ls.service.impl;

import com.ls.entity.Inventory;
import com.ls.mapper.InventoryMapper;
import com.ls.service.IInventoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-17
 */
@Service
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements IInventoryService {

}
