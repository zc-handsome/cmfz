<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/echarts/china.js"></script>
<script src="${pageContext.request.contextPath}/echarts/goeasy-1.0.3.js"></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="china" style="width: 600px;height: 600px;margin-top: 30px;margin-left: 30px">

</div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('china'));

    function randomData() {
        return Math.round(Math.random() * 10000);
    }

    var option = {
        title: {
            text: '用户分布图',
            subtext: '2019年11月26日 最新数据',
            left: 'center'
        },
        tooltip: {},
        // 说明
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 30000,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        // 工具箱
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }
            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
    $.ajax({
        url: "${pageContext.request.contextPath}/user/groupByProvince",
        type: "POST",
        dataType: "json",
        success: function (result) {
            myChart.setOption({
                series: [
                    {
                        data: result.y
                    },
                    {
                        data: result.n
                    }
                ]
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
                url: "${pageContext.request.contextPath}/user/groupByProvince",
                type: "POST",
                dataType: "json",
                success: function (result) {
                    myChart.setOption({
                        series: [
                            {
                                data: result.y
                            },
                            {
                                data: result.n
                            }
                        ]
                    });
                    alert("添加成功")
                }
            })
        }
    });


</script>












