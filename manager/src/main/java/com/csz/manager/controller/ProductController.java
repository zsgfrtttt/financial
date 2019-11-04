package com.csz.manager.controller;

import com.csz.entity.Product;
import com.csz.manager.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@Api(tags = "product",description = "产品模块")
public class ProductController {
    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService mProductService;

    @ApiOperation(value = "创建产品",notes = "根据业务创建产品")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        return mProductService.addProduct(product);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable String id) {
        return mProductService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<Product> query(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, String status,
                               @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }

        Pageable pageable = PageRequest.of(pageNum,pageSize);
        return mProductService.query(idList,minRewardRate,maxRewardRate,statusList,pageable);
    }


}
