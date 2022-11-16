package com.wasabi.projetowasabiweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    static AcessoBD bd;
    static Connection conn;
    @Override
    public void init() throws ServletException {
        try {
            bd.conectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        HttpSession session = request.getSession();
        if ("admin@admin.com".equals(email) && "admin".equals(senha)){
            session.setAttribute("email", email);
            response.sendRedirect("admin/adminHome.jsp");
        } else {
            int z = 0;
            try {
                conn = bd.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * from cliente WHERE email = '"+email+"' AND senha = '"+senha+"'");
                while (rs.next()){
                    z = 1;
                    session.setAttribute("email", email);
                    response.sendRedirect("home.jsp");
                }
                if (z == 0){
                    response.sendRedirect("login.jsp?msg=user_not_found");
                }
            } catch (ClassNotFoundException | SQLException e) {
                response.sendRedirect("login.jsp?msg=invalid");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {
        try {
            bd.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
