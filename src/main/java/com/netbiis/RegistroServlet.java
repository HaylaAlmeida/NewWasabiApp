package com.wasabi.projetowasabiweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegistroServlet", value = "/RegistroServlet")
public class RegistroServlet extends HttpServlet {
    static AcessoBD bd;
    static Connection conn;
    @Override
    public void init() throws ServletException {
        super.init();
        bd = new AcessoBD();
        try {
            bd.conectar();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String senha = request.getParameter("senha");
        try {
            conn = bd.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO cliente (nome, email, cpf, telefone, endereço," +
                    "senha) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, cpf);
            ps.setString(4, telefone);
            ps.setString(5, endereco);
            ps.setString(6, senha);
            response.sendRedirect("registro.jsp?msg=valid");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("registro.jsp?msg=invalid");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            bd.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}