<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../css/bootstrap-fileinput.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../../js/bootstrap-fileinput.js"></script>
</head>

<body>
<div class="form-group">
    <div class="col-md-8">
        <div class="fileinput fileinput-new" data-provides="fileinput" id="uploadImageDiv">
            <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                <img src="${(productInfo.productIcon)!''}" alt="" />
            </div>
            <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div>
            <div>
                <span class="btn default btn-file">
                    <span class="fileinput-new">选择图片</span>
                    <span class="fileinput-exists">更改</span>
                    <input type="file" name="uploadImage" id="uploadImage" />
                </span>
                <a href="#" class="btn default fileinput-exists" data-dismiss="fileinput">移除</a>
                <span>请选择1M以内图片</span>
            </div>
        </div>
        <div id="titleImageError" style="color: #a94442"></div>
    </div>
</div>
</body>
<script>
    var url = '';
    $("#uploadImage").fileupload({
        url : ROOT + "/security/company/uploadFile",
        dataType : 'json',
        autoUpload : false,
        acceptFileTypes : /(gif|jpe?g|png)$/i,
        maxFileSize : 1000000, // 1 MB
        imageMaxWidth : 100,
        imageMaxHeight : 100,
        messages : {
            acceptFileTypes : '文件类型不匹配',
            maxFileSize : '文件过大',
            minFileSize : '文件过小'
        }
    }).on("fileuploadadd", function(e, data) {
        // 当文件添加上去时候调用
        $("#titleImageError").html("");
        data.submit()
    }).on("fileuploaddone", function(e, data) {
        // 上传完成时调用
        if (data.result.returnState == "ERROR") {
            alert("上传失败")
            return;
        }
        url = data.result.returnData.url;
    });

    function updateMsg() {
        $.ajax({
            url : ROOT + "/security/company/updateInfo",
            data : {
                id : $("#companyId").val(),
                image : url,
                companyName : $("#companyName").val(),
                companySite : $("#companySite").val(),
                companyLinker : $("#companyLinker").val(),
                companyTel : $("#companyTel").val()
            },
            type : "post",
            success : function(data) {
                if (data.returnState == "OK") {
                    layer.msg('操作成功', {
                        icon : 6,
                        shade : 0.01,
                        offset : [ '57px', '1100px' ]
                    });
                    $table.bootstrapTable('refresh');
                } else {
                    layer.msg('操作失败', {
                        icon : 5,
                        shade : 0.01,
                        offset : [ '90px', '900px' ]
                    });
                }
            }
        });
    }
</script>

</html>