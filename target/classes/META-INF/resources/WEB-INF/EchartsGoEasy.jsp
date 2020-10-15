<%@page isELIgnored="false" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <script src="${path}/js/echarts.js"></script>
        <script src="${path}/static/jqgrid/js/jquery.min.js"></script>
        <script type="text/javascript" src="${path}/js/goeasy-1.0.17.js"></script>

        <script type="text/javascript">

            //初始化GoEasy对象
            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-9ae9e05cdd2a4c568224055de1132131", //替换为您的应用appkey
            });


            $(function(){

                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));


                //接收消息
                goEasy.subscribe({
                    channel: "百知2004Channel", //替换为您自己的channel
                    onMessage: function (message) {
                        //alert("Channel:" + message.channel + " content:" + message.content);


                        //将json字符串转为javascript对象
                        var data=JSON.parse(message.content);

                        // 指定图表的配置项和数据
                        var option = {
                            title: {
                                text: '用户注册统计图',  //标题
                                link:"${path}/main.jsp",
                                subtext:"纯属虚构",
                            },
                            tooltip: {}, //鼠标提示
                            legend: {
                                data:['男孩',"女孩"]  //选项卡
                            },
                            xAxis: {
                                data: data.month //横轴
                            },
                            yAxis: {},  //纵轴
                            series: [{
                                name: '男孩',
                                type: 'line',
                                data: data.boys
                            },{
                                name: '女孩',
                                type: 'bar',
                                data:data.girls
                            }]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);

                    }
                });

            });

        </script>
    </head>
    <body>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div id="main" style="width: 600px;height:400px;"></div>

    </body>
</html>