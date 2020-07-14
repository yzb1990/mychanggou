package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/12 - 9:28 上午 - 星期日
 * @Package : com.changgou.goods.service
 * @ProjectName : changgou
 */
public interface BrandService {
    List<Brand> findAll();
    Brand findoneById(int id);
    int addBrand(Brand brand);

    void updateBrand(Brand brand);

    void delBrand(int brandid);
    List<Brand> findLikeAll(Brand brand);
    PageInfo<Brand> findpage(Integer page,Integer size);

    PageInfo<Brand> findPageAndbrand(Brand brand,Integer page,Integer size);
}
