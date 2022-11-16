<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/registro.css">
<title>Login</title>
</head>
<body>
<div id='container'>
  <div class='signup'>
     <form action="LoginServlet" method="post">
     <input type="email" name="email" placeholder="E-mail" required>
     <input type="password" name="senha" placeholder="Senha" required>
     <input type="submit" value="Entrar">
     </form>
      <h2><a href="registro.jsp">Registrar</a></h2>
       <h2><a href="">Esqueceu a senha?</a></h2>
  </div>
  <div class='whysignLogin'>
      <%
      String msg = request.getParameter("msg");
      if("user_not_found".equals(msg))
      {
      %>
      <h1>E-mail ou senha incorreto(a)!</h1>
      <%} %>
      <%if("invalid".equals(msg))
      {
      %>
      <h1>Algo deu errado, tente novamente!</h1>
      <%} %>
    <h2>Restaurante Wasabi</h2>
    <p>The Online Shopping System is the application that allows the users to shop online without going to the shops to buy them.</p>
  </div>
</div>

</body>
</html>