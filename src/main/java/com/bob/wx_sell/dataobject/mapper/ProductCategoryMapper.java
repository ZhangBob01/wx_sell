package com.bob.wx_sell.dataobject.mapper;

import com.bob.wx_sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Auther: toudaizhi
 * @Date: 2019-02-28 19:50
 * @Description:
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByMap(Map<String,Object> map);

    @Insert("insert into product_category(category_name,category_type) values (#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type = #{categoryType}")
    @Results({
            @Result(column = "category_id", property = "categoryId"),
            @Result(column = "category_name", property = "categoryName"),
            @Result(column = "category_type", property = "categoryType")
    })
    ProductCategory findByCategoryType(Integer categoryType);

    @Select("select * from product_category where category_name like CONCAT(CONCAT('%', #{description}), '%')")
    @Results({
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    List<ProductCategory> findByCategoryName(String categoryName);

    @Select("select * from product_category limit ${page},${size}")
    List<ProductCategory> findAll(Pageable pageable);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByCategoryType(@Param("categoryName") String categoryName,
                             @Param("categoryType") Integer categoryType);

    @Update("update product_category set category_name = #{categoryName} where category_type = #{categoryType}")
    int updateByObject(ProductCategory productCategory);

    @Delete("delete from product_category where category_type = #{categoryType}")
    int deleteByCategoryType(Integer categoryType);

    @Select("<script>"
            + "SELECT * FROM product_category WHERE category_type IN "
            + "<foreach item='item' index='index' collection='typeList' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    @Results(value = {
            @Result(column = "category_id",property = "categoryId"),
            @Result(column = "category_name",property = "categoryName"),
            @Result(column = "category_type",property = "categoryType")
    })
    List<ProductCategory> findProductCategoryList(@Param("typeList") List<Integer> typeList);

    ProductCategory selectByCategoryType(Integer categoryType);
}
