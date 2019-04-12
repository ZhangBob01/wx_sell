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
                            <form role="form" action="/seller/category/save" method="post">
                                <input hidden type="text" name="categoryId" value="${(productCategory.categoryId)!''}">
                                <div class="form-group">
                                    <label>名称</label><input type="text" value="${(productCategory.categoryName)!''}" class="form-control" name="categoryName" id="categoryName" />
                                </div>
                                <div class="form-group">
                                    <label>编号</label><input type="number" value="${(productCategory.categoryType)!''}" class="form-control" name="categoryType" id="categoryType" />
                                </div>

                                <button type="submit" class="btn btn-default">提交</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>