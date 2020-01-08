<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>love</title>
    <link href="favicon.ico" rel="shortcut icon" />
    <link href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript">
        function login() {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/selectOne",
                type:"POST",
                datatype:"JSON",
                data:$("#loginForm").serialize(),
                success:function (data) {
                    if (data!=null&data!=""){
                        alert(data);
                    }else {
                        location.href = "${pageContext.request.contextPath}/jsp/main.jsp";
                    }
                }

            })
        }
    </script>
</head>
<body style=" background: url(../img/图1.jpg); background-size: 100%;">


<div class="modal-dialog" style="margin-top: 10%;">
    <div class="modal-content">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">持明法洲</h4>
        </div>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath}/user/login">
        <div class="modal-body" id = "model-body">
            <div class="form-group">
                <input type="text" class="form-control"placeholder="用户名" autocomplete="off" name="username">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" autocomplete="off" name="password">
            </div>
            <div class="form-group">
                <input type="text" name="clientCode" id="vcode" class="form-control" placeholder="验证码" autocomplete="off">
                <div class="col-sm-6">
                    <img id="img" src="${pageContext.request.contextPath }/admin/createImg"/>
                </div>
            </div>
            <span id="msg"></span>
        </div>
        <div class="modal-footer">
            <div class="form-group">
                <button type="button" class="btn btn-primary form-control" id="log" onclick="login()">登录</button>
            </div>
        </div>
        </form>
    </div>
</div>
</body>
</html>
