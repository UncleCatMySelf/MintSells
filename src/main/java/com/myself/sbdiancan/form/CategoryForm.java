package com.myself.sbdiancan.form;

import lombok.Data;

/**
 * 前端类目提交数据类型对象
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 17:01 2018\6\5 0005
 */
@Data
public class CategoryForm {

    private Integer categoryId;
    /** 类目名字. */
    private String categoryName;
    /** 类目编号. */
    private Integer categoryType;

}
