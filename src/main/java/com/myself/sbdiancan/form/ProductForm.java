package com.myself.sbdiancan.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 前端商品提交数据类型对象
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 16:20 2018\6\5 0005
 */
@Data
public class ProductForm {

    private String productId;

    /** 名字. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 商品缩略图. */
    private String productIcon;

    /** 类目编号. */
    private Integer categoryType;

}
