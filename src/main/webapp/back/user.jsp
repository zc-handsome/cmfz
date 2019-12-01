<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/goeasy-1.0.3.js"></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户注册量'
        },
        tooltip: {},
        legend: {
            data: ['男', '女']
        },
        xAxis: {
            data: ["近一周", "近两周", "近三周"]
        },
        yAxis: {},
        series: [{
            name: '男',
            type: 'bar',
            //  data: [5, 20, 36, 10, 10, 20]
        }, {
            name: '女',
            type: 'bar',
            //  data: [5, 20, 36, 10, 10, 20]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。

    myChart.setOption(option);
    $(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/user/findCount",
            type: "post",
            dataType: "json",
            success: function (result) {
                myChart.setOption({
                    series: [{
                        data: result.data1
                    },
                        {
                            data: result.data2
                        }]
                });
            }
        })
        //初始化GoEasy
        var goEasy = new GoEasy({
            host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-d0f674c74da94dacb0be49d387214a9e", //替换为您的应用appkey
        });
        //接受服务器端消息
        goEasy.subscribe({
            channel: "my_channel", //替换为您自己的channel
            onMessage: function (message) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/findCount",
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        myChart.setOption({
                            series: [{
                                data: result.data1
                            },
                                {
                                    data: result.data2
                                }]
                        });
                        alert(message.content);
                    }
                })
            }
        });
    })


</script>
</body>
</html>