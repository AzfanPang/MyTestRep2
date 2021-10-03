<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post" onsubmit="return checkAll()">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username" placeholder="请输入用户名" id="uname"><span id="usp"></span></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" placeholder="请输入密码"></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><input type="text" name="email" placeholder="请输入邮箱"></td>
        </tr>
        <tr>
            <td>手机号码:</td>
            <td><input type="text" name="phone" placeholder="请输入手机号码"></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
                <input type="radio" name="sex" value="0" id="nv" checked="checked"><label for="nv">女</label>
                <input type="radio" name="sex" value="1" id="nan"><label for="nan">男</label>
            </td>
        </tr>
        <tr>
            <td>年龄:</td>
            <td><input type="number" name="age"></td>
        </tr>
        <tr>
            <td>请输入验证码:</td>
            <td><input type="text" name="input_code"></td>
        </tr>
        <tr>
            <td colspan="2">
                <img id="myImg" src="${pageContext.request.contextPath}/code" onclick="changeImg()">
                <a href="javascript:void(0)" onclick="changeImg()">点击换一张</a>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交" >
                <%
                    String err = (String) request.getAttribute("cc_error");
                    if (err == null) {
                        out.write("");
                    } else {
                        out.write(err);
                    }
                %>
            </td>
        </tr>
    </table>
</form>
</body>
<script src="${pageContext.request.contextPath}/js/jquery.js" type="text/javascript" charset="utf-8"></script>
<script>

    function changeImg() {
        document.getElementById("myImg").src = "${pageContext.request.contextPath}/code?time=" + new Date().getTime();
    }

    $(function () {
        $('#uname').blur(function () {
            var uname=$(this).val().trim();
            if(!uname){
                $("#usp").html("<b style='color: red'>请填写用户名！<b>")
                return
            }
            $.get('${pageContext.request.contextPath}/checkUname?uname='+uname,function (res) {
                var msg=res.msg;
                if(msg=='yes'){
                    var text=res.text;
                    $("#usp").html("<b style='color: red'>"+text+"<b>")
                }else if(msg=='no'){
                    var text=res.text;
                    $("#usp").html("<b style='color: red'>"+text+"<b>")
                }
            })
        })
        $('form').submit(function (event) {
            var uname=$(this).val().trim();
            if(!uname){
                $("#usp").html("<b style='color: red'>请填写用户名！<b>")
                event.preventDefault();
            }
        })
    })

</script>
</html>
