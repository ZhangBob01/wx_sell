package com.bob.wx_sell.form;

import lombok.Data;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-25 15:07
 * @Description:
 */
@Data
public class CategoryForm {
    private Integer categoryId;
    /** 类目名称 */
    private String categoryName;
    /** 类目编号 */
    private Integer categoryType;
}
