<%--
  Created by IntelliJ IDEA.
  User: du1124
  Date: 2021/8/15
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
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
<%
    String username="";
    String password="";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username")){
                username=cookie.getValue();
            }else if(cookie.getName().equals("password")){
                password=cookie.getValue();
            }
        }
    }
%>
<form action="${pageContext.request.contextPath}/login" method="post">
    <table>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username" placeholder="请输入用户名" value="<%=username%>"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" placeholder="请输入密码" value="<%=password%>"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="checkbox" name="rem" id="id_rem" value="1"><label for="id_rem">记住密码</label></td>
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
                <input type="submit" value="提交">
                <div style="color:red">
                    <%
                        String msg = (String) request.getAttribute("msg");
                        if(msg==null){
                            out.write("");
                        }else{
                            out.write(msg);
                        }
                    %>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2"><a href="${pageContext.request.contextPath}/register.jsp">没有账号？</a></td>
        </tr>
    </table>
</form>
</body>
<script>
    function changeImg() {
        document.getElementById("myImg").src = "${pageContext.request.contextPath}/code?time=" + new Date().getTime();
    }
</script>
</html>
