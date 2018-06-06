package com.myself.sbdiancan.controller;

import com.myself.sbdiancan.dataobject.ProductCategory;
import com.myself.sbdiancan.dataobject.ProductInfo;
import com.myself.sbdiancan.dto.OrderDTO;
import com.myself.sbdiancan.exception.SbException;
import com.myself.sbdiancan.form.ProductForm;
import com.myself.sbdiancan.service.CategoryService;
import com.myself.sbdiancan.service.ProductService;
import com.myself.sbdiancan.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 20:21 2018\6\4 0004
 */
@Controller
@RequestMapping("/seller/product")
public class SbProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String, Object> map){
        PageRequest request = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.onSale(productId);
        } catch (SbException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sb/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/sb/seller/product/list");
        return new ModelAndView("common/success",map);
    }


    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                               Map<String,Object> map){
        try {
            productService.offSale(productId);
        } catch (SbException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sb/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url", "/sb/seller/product/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                      Map<String,Object> map){
        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo",productInfo);
        }

        //查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);

        return new ModelAndView("product/index",map);
    }

    /**
     * 保存和更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "",key = "")
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sb/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        ProductInfo productInfo =new ProductInfo();
        try {
            //如果id为空，说明是新增
            if (!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(form,productInfo);
            productService.save(productInfo);
        } catch (SbException e){
            map.put("msg", e.getMessage());
            map.put("url", "/sb/seller/product/index");
            return new ModelAndView("common/error",map);
        }

        map.put("url", "/sb/seller/product/list");
        return new ModelAndView("common/success",map);
    }

}
