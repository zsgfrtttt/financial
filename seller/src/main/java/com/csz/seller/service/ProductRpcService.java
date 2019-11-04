package com.csz.seller.service;

import com.csz.entity.Product;
import com.csz.entity.enums.ProductStatus;
import com.csz.rpc.ProductRpc;
import com.csz.rpc.domain.ProductRpcReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductRpcService {

    @Autowired
    private ProductRpc productRpc;

    public List<Product> findAll(){
        ProductRpcReq req= new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        req.setStatusLis(status);
        return productRpc.query(req);
    }

    public Product findOne(String id){
        return productRpc.findOne(id);
    }

    @PostConstruct
    public void test(){
        Product one = findOne("001");
        System.out.println("all : "+one);
    }
}
