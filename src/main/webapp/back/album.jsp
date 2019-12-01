<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {
        $("#tb").jqGrid({
            url: "${pageContext.request.contextPath}/album/findAllByPage",
            editurl: "${pageContext.request.contextPath}/album/edit",
            mtype: "POST",
            datatype: "json",
            colNames: ["标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发行时间", "上传时间", "插图"],
            colModel: [
                {name: "title", editable: true},
                {name: "score", editable: true},
                {name: "author", editable: true},
                {name: "announcer", editable: true},
                {name: "chapterNum", editable: true},
                {name: "albumIntro", editable: true},
                {name: "status", editable: true},
                {name: "issueTime", editable: true, edittype: "date"},
                {name: "uploadTime", editable: true, edittype: "date"},
                {
                    name: "picture", editable: true, edittype: "file",
                    formatter: function (cellValue, options, rowObject) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + cellValue + "'/>";
                    }
                }
            ],
            autowidth: true,
            styleUI: "Bootstrap",
            pager: "#albumPage",
            rowNum: 2,
            page: 1,
            rowList: [2, 4, 6],
            multiselect: true,
            viewrecords: true,
            height: "60%",
            subGrid: true,
            subGridRowExpanded: function (subGridId, albumId) {
                var tableId = subGridId + "table";
                var pagerId = subGridId + "pager";
                $("#" + subGridId).html(
                    "<table id=" + tableId + "></table>\n" +
                    "<div id=" + pagerId + "></div>"
                );
                $("#" + tableId).jqGrid({
                    url: "${pageContext.request.contextPath}/chapter/findAllByPage?albumId=" + albumId,
                    editurl: "${pageContext.request.contextPath}/chapter/edit?parentId=" + albumId,
                    datatype: "json",
                    colNames: ["标题", "大小", "时长", "上传时间", "音频", "操作"],
                    colModel: [
                        {name: "title", editable: true},
                        {
                            name: "size",
                            formatter: function (cellValue, options, rowObject) {
                                return cellValue + "M";
                            }
                        },
                        {name: "duration"},
                        {name: "uploadDate"},
                        {name: "mp3Name", editable: true, edittype: "file"},
                        {
                            name: "mp3Name", width: 420,
                            formatter: function (cellValue, options, rowObject) {
                                return "<audio width='50' src='${pageContext.request.contextPath}/chapter/" + cellValue + "' controls='controls'><audio>"
                            }
                        }
                    ],
                    autowidth: true,
                    styleUI: "Bootstrap",
                    pager: "#" + pagerId,
                    rowNum: 2,
                    page: 1,
                    rowList: [2, 4, 6],
                    multiselect: true,
                    viewrecords: true,
                    height: "60%"
                }).jqGrid("navGrid", "#" + pagerId,
                    {search: false},
                    {
                        closeAfterEdit: true,
                        beforeShowForm: function (obj) {
                            obj.find("#mp3Name").attr("disabled", true);
                        }
                    },
                    {
                        closeAfterAdd: true,
                        afterSubmit: function (response) {
                            var chapterId = response.responseText;
                            $.ajaxFileUpload({
                                url: "${pageContext.request.contextPath}/chapter/updateMp3Name",
                                fileElementId: "mp3Name",
                                data: {cid: chapterId},
                                success: function (data) {
                                    $("#" + tableId).trigger("reloadGrid");
                                    $("#tb").trigger("reloadGrid");
                                }
                            })
                            return "haha"
                        },

                    },
                    {
                        closeAfterDel: true


                    }
                )
            }
        }).jqGrid("navGrid", "#albumPage",
            {search: false},
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#picture").attr("disabled", true);
                    obj.find("#issueTime").attr("readonly", true);
                    obj.find("#uploadTime").attr("readonly", true);
                    obj.find("#chapterNum").attr("readonly", true);
                    obj.find("#author").attr("readonly", true);
                    obj.find("#announcer").attr("readonly", true);
                }
            },
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/updateImgUrl",
                        fileElementId: "picture",
                        data: {aid: albumId},
                        success: function () {
                            $("#tb").trigger("reloadGrid");
                        }
                    });
                    return "haha"
                }
            },
            {
                closeAfterDel: true
            }
        )
    })
</script>


<table id="tb"></table>
<div id="albumPage" style="height: 50px"></div>

