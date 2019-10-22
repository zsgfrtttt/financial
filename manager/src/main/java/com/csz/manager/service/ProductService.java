package com.csz.manager.service;


import com.csz.common.util.TextUtil;
import com.csz.entity.Product;
import com.csz.entity.enums.ProductStatus;
import com.csz.manager.error.ErrorEnum;
import com.csz.manager.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository mProductRepository;

    public Product addProduct(Product product){
        System.out.println("创建产品,参数{}" + product);
        checkProduct(product);
        setDefaultField(product);
        Product result = mProductRepository.save(product);
        System.out.println("创建产品,结果{}" + result);
        return result;
    }

    public Product findOne(String id){
        System.out.println("查询产品,id{}"+id);
        if(TextUtil.isEmpty(id)) return null;
        Product result = mProductRepository.findById(id).get();
        System.out.println("查询产品,结果{}"+result);
        return result;
    }

    public Page<Product> query(List<String> idList, BigDecimal minRewardRate, BigDecimal maxRewardRate, List<String> statusList, Pageable pageable) {
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Expression<String> id = root.get("id");
                Expression<BigDecimal> rewardRate = root.get("rewardRate");
                Expression<BigDecimal> status = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if (idList!= null && !idList.isEmpty()){
                    predicates.add(id.in(idList));
                }
                if (statusList!= null && !statusList.isEmpty()){
                    predicates.add(status.in(statusList));
                }
                if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) < 0){
                   //ge大于等于
                    predicates.add(criteriaBuilder.ge(rewardRate,minRewardRate));
                }
                if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) < 0){
                    //le小于等于
                    predicates.add(criteriaBuilder.le(rewardRate,maxRewardRate));
                }

                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };

        Page<Product> all = mProductRepository.findAll(specification, pageable);
        return all;
    }

    private void checkProduct(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_NULL.getCode());
        Assert.isTrue(BigDecimal.valueOf(30).compareTo(product.getRewardRate()) > 0
                && BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0,ErrorEnum.REWARD_RATE_NOT_VALID.getCode());
        Assert.isTrue(product.getStepAmount().longValue() != 0,ErrorEnum.STEP_AMOUNT_ZREO.getCode());
    }

    private void setDefaultField(Product product) {
        if (product.getCreateAt() == null){
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null){
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount()== null){
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null){
            product.setLockTerm(0);
        }
        if (product.getStatus() == null){
            product.setStatus(ProductStatus.AUDINTING.name());
        }
    }


}
