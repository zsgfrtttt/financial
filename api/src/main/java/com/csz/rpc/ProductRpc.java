package com.csz.rpc;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.csz.entity.Product;
import com.csz.rpc.domain.ProductRpcReq;

import java.util.List;

@JsonRpcService("rpc/product")
public interface ProductRpc {

    List<Product> query(ProductRpcReq req);

    Product findOne(String id);

}
