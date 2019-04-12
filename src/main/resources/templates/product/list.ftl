<html>
    <#include "../common/header.ftl">
    <body>
    <div id="wrapper" class="toggled">

        <#--边栏sidebar-->
        <#include "../common/nav.ftl">

        <#--主要内容content-->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>商品ID</th>
                                    <th>名称</th>
                                    <th>图片</th>
                                    <th>单价</th>
                                    <th>库存</th>
                                    <th>描述</th>
                                    <th>类目</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th colspan="2">操作</th>

                                </tr>
                                </thead>
                                <tbody>
                                <#list productInfoPage.getContent() as product>
                                <tr>
                                    <td>${product.productId}</td>
                                    <td>${product.productName}</td>
                                    <td><img src="${product.productIcon}" height="100" width="100" alt="图片"></td>
                                    <td>${product.productPrice}</td>
                                    <td>${product.productStock}</td>
                                    <td>${product.productDescripttion}</td>
                                    <td>${product.categoryType}</td>
                                    <td>${product.createTime}</td>
                                    <td>${product.updateTime}</td>
                                    <td><a href="/seller/product/index?productId=${product.productId}">修改</a></td>
                                    <#if product.getProductStatusEnum().message =='在架'>
                                        <td><a href="/seller/product/off_sale?productId=${product.productId}">下架</a></td>
                                    <#else>
                                        <td><a href="/seller/product/on_sale?productId=${product.productId}">上架</a></td>
                                    </#if>

                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                                <#if currentPage lte 1>
                                    <li class="disabled"><a href="#">上一页</a></li>
                                <#else>
                                    <li><a href="/seller/product/list?page=${currentPage-1}&size=${size}">上一页</a></li>
                                </#if>

                                <#list 1..productInfoPage.getTotalPages() as index>
                                    <#if currentPage == index>
                                        <li class="disabled"><a>${index}</a></li>
                                    <#else>
                                        <li><a href="/seller/product/list?page=${index}&size=${size}">${index}</a></li>
                                    </#if>
                                </#list>
                                <#if currentPage gte productInfoPage.getTotalPages()>
                                    <li class="disabled"><a href="#">下一页</a></li>
                                <#else>
                                    <li><a href="/seller/product/list?page=${currentPage+1}&size=${size}">下一页</a></li>
                                </#if>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>