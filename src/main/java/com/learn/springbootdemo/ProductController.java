package com.learn.springbootdemo;

import com.learn.springbootdemo.dao.domain.Product;
import com.learn.springbootdemo.dao.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public Product getProductInfo(@PathVariable("id") long productId) {
        Product product = productMapper.select(productId);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProductInfo(
            @PathVariable("id") long productId,
            @RequestBody Product newProduct) {
        Product product = null;
        try {
            product = productMapper.select(productId);
        } catch (Exception e) {
            System.out.println("Get exception: " + e);
        }
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        productMapper.update(product);
        return product;
    }
}