<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <!--在手机网站，都需要加上视口约束！！！-->
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no"/>
    <!--以最新的内核渲染页面-->
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>登录</title>
    <!--引入css文件-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bs/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bs/css/bootstrap-theme.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/yxue/yxue.js"></script>
    <style type="text/css"></style>
    <!--引入jQuery-->
    <script src="${pageContext.request.contextPath}/static/bs/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <!--引入js文件-->
    <script src="${pageContext.request.contextPath}/static/bs/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function time(obj,wait) {
            let $this = $(obj);

            if (wait==0) {
                // 如果等待时间wait为0的时候，设置按钮可以触发
                $this.css('pointer-events','');
                $this.html('发送验证码');
                wait = 60;
            } else {
                // 如果等待时间wait大于0,设置按钮禁用（不可以触发事件）
                $this.css('pointer-events','none');
                $this.html(wait+'秒后可以重新发送');
                // 手动减秒
                wait--;
                setTimeout(function () {
                    // 递归调用time函数
                    time(obj, wait);
                }, 1000);
            }
        }
        // 点击发送验证码
       $(function () {
           $('#sendPhone').click(function () {
               // 控制发送验证码的周期
               time(this, 60);

               // 获取手机号 发送到后台
               alert('是否发送验证码？');
               var  phone=$('#phone').val();
               alert(phone)
               $.ajax({
                   url:'${path}/admin/sendPhoneCode',
                   data:"phone="+phone,
                   type:"post",
                   success:function (re) {

                   }
               });
           });
       })
    </script>
    <style type="text/css">


    </style>
</head>
<body class="container" >
<div class="col-md-4 col-md-offset-4" style="align:center;"><br/><br/><br/><br/>
    <div class="panel" style="align:center;width: 300px;">
        <div class="middle-box text-center loginscreen  animated fadeInDown">
            <div >
                <div>

                    <h1 class="logo-name"><img src="${pageContext.request.contextPath}/static/img/yx-icon.png"></h1>


                </div>
                <h3>应学后台管理系统</h3>

                <form action="${pageContext.request.contextPath}/admin/adminLogin" method="post">
                    <input type="hidden" value="1" name="id">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="用户名" name="username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="密码" name="password">
                    </div>
                    <div class="form-group">
                        <input type="txet" class="form-control" placeholder="手机号" id="phone">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="验证码" name="password">
                        <a id="sendPhone"  class="btn btn-default">发送验证码</a>
                    </div>

                    <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

                    <p class="text-muted text-center"><a href="login.jsp#">
                        <small>忘记密码了？</small>
                    </a> | <a href="#">注册一个新账号</a>
                    </p>
                </form>

            </div>
        </div>
    </div>

</div>

</body>
</html>