package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/12 - 9:27 上午 - 星期日
 * @Package : com.changgou.goods.service.impl
 * @ProjectName : changgou
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<Brand> findAll() {

        return brandMapper.selectAll();
    }

    @Override
    public Brand findoneById(int id) {

        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addBrand(Brand brand) {
        int i = brandMapper.insertSelective(brand);
        return i;
    }

    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delBrand(int brandId) {
        brandMapper.deleteByPrimaryKey(brandId);
    }

    @Override
    public List<Brand> findLikeAll(Brand brand) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name","%"+brand.getName()+"%");
            }

            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }

        return brandMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Brand> findpage(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Brand> brands = brandMapper.selectAll();
        return new PageInfo<Brand>(brands);
    }

    @Override
    public PageInfo<Brand> findPageAndbrand(Brand brand, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        if (brand != null) {
            if (!StringUtils.isEmpty(brand.getName())) {
                criteria.andLike("name","%"+brand.getName()+"%");
            }

            if (!StringUtils.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }

        List<Brand> brands = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brands);
    }


}
