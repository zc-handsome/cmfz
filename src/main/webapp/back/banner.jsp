<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>

<%--
         private String id;
    private String title;
    private String status;
    private Date createTime;
    private String url;
    private String descript;
--%>
<script>
    $(function () {
        $("#tb").jqGrid({
            //访问路径
            url: "${pageContext.request.contextPath}/banner/findAllByPage",
            editurl: "${pageContext.request.contextPath}/banner/edit",
            mtype: "POST",
            datatype: "json",
            colNames: ["编号", "标题", "状态", "描述", "创建时间", "图片"],
            colModel: [
                {
                    name: "id"
                },
                {
                    name: "title",
                    editable: true
                },
                {
                    name: "status",
                    editable: true,
                    edittype: "select",
                    editoptions: {
                        value: "y:展示;n:不展示"
                    },
                    formatter: function (a, b, c) {
                        if (a == 'y') {
                            return '展示';
                        } else {
                            return '不展示';
                        }
                    }
                },
                {
                    name: "descript",
                    editable: true
                },
                {
                    name: "createTime"
                },
                {
                    name: "url", editable: true, edittype: "file",
                    formatter: function (cellValue, options, rowObject) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + cellValue + "'/>";
                    }
                }
            ],
            autowidth: true,
            styleUI: "Bootstrap",
            pager: "#bannerPage",
            rowNum: 4,
            page: 1,
            rowList: [2, 4, 6, 8],
            multiselect: true,
            viewrecords: true,
            height: "60%"
        }).jqGrid("navGrid", "#bannerPage",
            {
                //处理前台页面按钮组的样式以及展示后者不展示。
                search: false
            },
            {
                closeAfterEdit: true,
                //控制编辑按钮，在编辑之前或者之后要进行的额外操作
                beforeShowForm: function (obj) {
                    obj.find("#url").attr("disabled", true)
                    obj.find("#createTime").attr("disabled", true)
                }
            },
            {
                //控制添加按钮，在添加之前或者之后要进行的额外操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/updateUrl",
                        fileElementId: "url",
                        data: {bId: bannerId},
                        success: function (data) {
                        }
                    });
                    return "sdfds"
                }

            },
            {
                //控制删除按钮，在删除之前或者之后要进行的额外操作
                closeAfterDel: true
            }
        )

        $("#btn").click(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/banner/exportFile",
                type: "POST",
                data: {xlsName: "轮播图.xls"},
                success: function (data) {
                    alert("导出成功！");
                }
            })
        })
    })
</script>


<!--表格-->
<table id="tb"></table>
<!--分页区-->
<div style="height: 50px" id="bannerPage">

</div>

<div style="margin-top: 10px">
    <!--导出数据到xls表格-->
    <a style="float: right;" href="${pageContext.request.contextPath}/banner/exportFile?xlsName=轮播图.xls"
       class="btn btn-success" id="btn">导出表格</a>
</div>

