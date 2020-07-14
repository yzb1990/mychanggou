package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.Net;

import java.util.List;

/**
 * @Author : yuzebo <511729587@qq.com>
 * @Date : 2020/7/12 - 9:29 上午 - 星期日
 * @Package : com.changgou.goods.controller
 * @ProjectName : changgou
 */
@RestController
@RequestMapping(value = "/brand")
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping
    public Result<List<Brand>>  findAll(){
        List<Brand> brandList = brandService.findAll();

        return new Result<List<Brand>>(true, StatusCode.OK,"查询品牌列表成功",brandList);
    }

    @GetMapping(value = "/{id}")
    public  Result<Brand> findoneById(@PathVariable Integer id){
        Brand brand = brandService.findoneById(id);
        return new Result<Brand>(true,StatusCode.OK,"根据id查询商品成功",brand);
    }

    @PostMapping(value = "/addbrand")
    public Result addBrand(@RequestBody Brand brand){
        int i = brandService.addBrand(brand);
        return new Result(true,StatusCode.OK,"添加商品成功");
    }

    @PutMapping(value = "/update/{id}")
    public Result updateBrandById(@PathVariable Integer id ,@RequestBody Brand brand){
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true,StatusCode.OK,"更新商品成功");
    }

    @DeleteMapping(value = "/del/{id}")
    public Result delBrandById(@PathVariable Integer id){
        brandService.delBrand(id);
        return new Result(true,StatusCode.OK,"删除商品成功");
    }

    @PostMapping(value = "/findlike")
    public Result<List<Brand>> findlikeAll(@RequestBody Brand brand){
        List<Brand> brandList = brandService.findLikeAll(brand);

        return  new Result<List<Brand>>(true,StatusCode.OK,"模糊查询成功",brandList);
    }

    @GetMapping(value = "/findpage/{page}/{size}")
    public Result<PageInfo<Brand>> findpage(@PathVariable Integer page,@PathVariable Integer size){
        PageInfo<Brand> findpage = brandService.findpage(page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",findpage);
    }

    @PostMapping(value = "/findpageAndbrand/{page}/{size}")
    public  Result<PageInfo<Brand>> findpageAndbrand(@RequestBody Brand brand,@PathVariable(value = "page") Integer page
            ,@PathVariable(value = "size") Integer size){
        //int a = 1/0;
        PageInfo<Brand> pageAndbrand = brandService.findPageAndbrand(brand, page, size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"条件分页查询成功",pageAndbrand);
    }
}
