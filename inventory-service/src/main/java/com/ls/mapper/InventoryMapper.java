package com.ls.mapper;

import com.ls.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-17
 */
public interface InventoryMapper extends BaseMapper<Inventory> {
    @Update("update inventory set product_num=product_num-#{productNum} where product_id=#{productId}")
    void subInventoryNumByProductId(@Param("productId") Long productId, @Param("productNum")Long productNum);
}
