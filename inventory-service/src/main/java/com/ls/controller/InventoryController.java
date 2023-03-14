package com.ls.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ls.entity.Inventory;
import com.ls.exception.DataResult;
import com.ls.service.IInventoryService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lingsheng
 * @since 2022-10-17
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Resource
    private IInventoryService inventoryService;
    @PostMapping("/saveInventory")
    public DataResult saveInventory(@RequestBody Inventory inventory){
        return new DataResult(inventoryService.save(inventory));
    }
    @DeleteMapping("/deleteInventory")
    public DataResult deleteInventory(BigInteger id){
        return new DataResult(inventoryService.getBaseMapper().deleteById(id));
    }
    @PostMapping("/getInventory")
    public DataResult getInventory(@RequestBody Inventory inventory){
        QueryWrapper<Inventory> query = Wrappers.query();
        query.eq("id",inventory.getId());
        return new DataResult(inventoryService.getOne(query));
    }
    @ApiOperation("根据产品id删除改产品指定的库存量")
    @GetMapping("/subInventoryNumByProductId")
    public DataResult subInventoryNumByProductId(@Param("productId")Long productId,@Param("productNum")Long productNum){
        UpdateWrapper<Inventory> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id",productId).set("product_num","${product_num-"+productNum+"}");
        return new DataResult(inventoryService.update(updateWrapper));
    }
}