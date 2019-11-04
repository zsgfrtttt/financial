package com.csz.manager.rpc;

import com.csz.entity.Product;
import com.csz.manager.service.ProductService;
import com.csz.rpc.ProductRpc;
import com.csz.rpc.domain.ProductRpcReq;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

    @Autowired
    ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        Pageable pageable = PageRequest.of(0,1000, Sort.Direction.DESC,"rewardRate");
        Page<Product> page = productService.query(req.getIdList(), req.getMinRewardRate(), req.getMaxRewardRate(), req.getStatusLis(), pageable);
        return page.getContent();
    }

    @Override
    public Product findOne(String id) {
        return productService.findOne(id);
    }
}
