package com.learn.springbootdemo.dao.mapper;

import com.learn.springbootdemo.dao.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ProductMapper {
    Product select(@Param("id") long id);

    void update(Product product);
}
