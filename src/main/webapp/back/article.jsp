<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {
        $("#tb").jqGrid({
            url: "${pageContext.request.contextPath}/article/findAllByPage",
            mtype: "Post",
            datatype: "json",
            colNames: ["id", "标题", "内容", "作者", "状态", "上传时间", "发布时间", "操作"],
            colModel: [
                {name: "id", hidden: true},
                {name: "title"},
                {name: "content", hidden: true},
                {name: "author"},
                {
                    name: "status",
                    formatter: function (a, b, c) {
                        if (a == "y") {
                            return "展示";
                        } else {
                            return "不展示";
                        }
                    }

                },
                {name: "uploadTime"},
                {name: "issueTime"},
                {
                    name: "",
                    formatter: function (a, b, c) {
                        return "<a href='#' onclick=\"articleDetail('" + c.id + "')\">文章详情</a>";
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            pager: "#articlePage",
            rowNum: 4,
            page: 1,
            rowList: [2, 4, 6, 8],
            multiselect: true,
            viewrecords: true,
            height: "60%"
        })
    })

    //添加文章
    function showModal() {
        //初始化模态框
        $("#myModal").modal("show");
        $("#modal_footer").html("<button type=\"button\" class=\"btn btn-primary\" onclick=\"addArticle()\">添加</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>")
        //初始化富文本编辑器 #editor文本域的id
        KindEditor.create("#editor", {
            //提供图片上传的功能
            uploadJson: "${pageContext.request.contextPath}/KindEditor/uploadImg",
            filePostName: "img",//上传的文件的名字
            fileManagerJson: "${pageContext.request.contextPath}/KindEditor/getAllImg",//查看上传到图库中的所有图片
            allowFileManager: true,//图片库的按钮
            afterBlur: function () {
                this.sync();//失去焦点后提交编辑器的数据
            }
        });
        $("#addArticleFrom")[0].reset();//重置form表单的数据
        KindEditor.html("#editor", "");//清空编辑器的内容防止再次点击上次输入的数据还存在
    }

    //点击保存按钮添加数据
    function addArticle() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/addArticle",
            type: "post",
            data: $("#addArticleFrom").serialize(),//表单的序列化
            success: function (data) {
                alert("添加成功")
                //提交数据后，关闭模态框
                $("#myModal").modal("hide");
                $("#tb").trigger("reloadGrid")
            }
        })
    }


    //文章详情（可修改）
    function articleDetail(id) {
        $("#addArticleFrom")[0].reset();
        $("#myModal").modal("show");
        var value = $("#tb").getRowData(id);//根据id获取一行数据
        $("#title").val(value.title);
        $("#author").val(value.author);
        if (value.status == "展示") {//获取的是单元格中的实际展示的值
            $("#status").val('y');
        } else {
            $("#status").val('n');
        }
        $("#modal_footer").html("<button type=\"button\" class=\"btn btn-primary\" onclick=\"updateArticle('" + id + "')\">保存修改</button>\n" +
            "                    <button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>")
        KindEditor.create("#editor", {
            uploadJson: "${pageContext.request.contextPath}/KindEditor/uploadImg",
            filePostName: "img",//上传的文件的名字
            fileManagerJson: "${pageContext.request.contextPath}/KindEditor/getAllImg",//查看上传到图库中的所有图片
            allowFileManager: true,//图片库的按钮
            afterBlur: function () {
                this.sync();//失去焦点后提交编辑器的数据
            }
        });
        KindEditor.html("#editor", "");//把之前填写的清空
        KindEditor.appendHtml("#editor", value.content);
    }

    //修改文章内容
    function updateArticle(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/updateArticle?id=" + id,
            type: "post",
            data: $("#addArticleFrom").serialize(),
            success: function (data) {
                alert("修改成功")
                $("#myModal").modal("hide");
                $("#tb").trigger("reloadGrid")
            }

        })
    }

</script>
<!--头部标签页-->
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">文章列表</a></li>
        <li role="presentation"><a href="#profile" onclick="showModal()" aria-controls="profile" role="tab"
                                   data-toggle="tab">添加文章</a></li>

    </ul>

</div>


<table id="tb"></table>

<div id="articlePage" style="height: 50px"></div>


<div class="modal fade" id="myModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">编辑用户信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/article/editArticle" class="form-horizontal"
                      id="addArticleFrom">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author" placeholder="请输入作者" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status">
                                <option value="y">展示</option>
                                <option value="n">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea id="editor" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg" name="insertImg" hidden>
                </form>
            </div>
            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer">
                <%--<button type="button" class="btn btn-primary" onclick="addArticle()">保存</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>--%>
            </div>
        </div>
    </div>
</div>
