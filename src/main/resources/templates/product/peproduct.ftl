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
                        <form role="form" action="/seller/product/save" method="post">
                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
                            <div class="form-group">
                                <label>名称</label><input type="text" value="${(productInfo.productName)!''}" class="form-control" name="productName" id="productName" />
                            </div>
                            <div class="form-group">
                                <label>价格</label><input type="text" value="${(productInfo.productPrice)!''}" class="form-control" name="productPrice" id="productPrice" />
                            </div>
                            <div class="form-group">
                                <label>库存</label><input type="number" value="${(productInfo.productStock)!''}" class="form-control" name="productStock" id="productStock" />
                            </div>
                            <div class="form-group">
                                <label>描述</label><input type="text" value="${(productInfo.productDescripttion)!''}" class="form-control" name="productDescripttion" id="productDescripttion" />
                            </div>
                            <div class="form-group">
                                <label>图片</label><img src="${(productInfo.productIcon)!''}">
                                <input type="text" hidden="hidden" value="${(productInfo.productIcon)!''}" class="form-control" name="productIcon" id="productIcon" />

                                <div class="file-loading">
                                    <input id="input-id" type="file">
                                    <p class="help-block">支持jpg、jpeg、png、gif格式，大小不超过1M</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>类目</label>
                                <select id="categoryType" name="categoryType" class="form-control">
                                    <#list productCategoryList as productCategory>
                                    <option value="${(productCategory.categoryType)}"
                                        <#if (productInfo.categoryType)?? && productInfo.categoryType == productCategory.categoryType>
                                            selected
                                        </#if>
                                    >${(productCategory.categoryName)}</option>
                                    </#list>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.min.js"></script>
    <script>

        $(function () {
            var initialPreview = [];
            if ('${(productInfo.productIcon)!""}' != '') {
                initialPreview = "<img class='kv-preview-data file-preview-image' src='${(productInfo.productIcon)!""}'>"
            }

            $("#input-id").fileinput({
                uploadUrl: '/image/upload',
                language: 'zh',
                browseClass: "btn btn-primary btn-block",
                showCaption: false,
                showRemove: false,
                showUpload: false,
                allowedFileExtensions: [ 'jpg', 'jpeg', 'png', 'gif' ],
                maxFileSize: 1024,
                autoReplace: true,
                overwriteInitial: true,
                maxFileCount: 1,
                initialPreview: initialPreview,
            });
        });
        //上传完成设置表单内容
        $('#input-id').on('fileuploaded', function(event, data, previewId, index) {
            if (data.response.code != 0) {
                alert(data.response.msg)
                return
            }
            $('#productIcon').val(data.response.data.fileName)
        });
    </script>
    </body>
</html>