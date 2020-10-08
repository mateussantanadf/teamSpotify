/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.model.Usuario;
import br.com.mateus.teamspotify.dao.DataSource;
import br.com.mateus.teamspotify.dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.runtime.ECMAErrors;

/**
 *
 * @author Mateus
 */
public class EfetivaCadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // receber dados do formulário
        // criar um objeto usuario com estes dados (mas que ainda não tem ID)
        // instanciar o datasource e o Dao
        // gravar no banco
        // dependendo do resultado, vamos trabalhar em qual pagina retornar
        String pagina = "/myaccount.jsp";
        String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        
        DataSource datasource = new DataSource();
        UsuarioDAO usuarioDAO = new UsuarioDAO(datasource);
        usuarioDAO.create(usuario);
        System.out.println(usuario);
        
        try{
        datasource.getConnection().close();
        }
        catch(SQLException ex){
            System.out.println("Erro ao fechar conexao "+ex.getMessage());                      
            request.setAttribute("erroMSG", "Erro ao criar nova conta de usuario");
            pagina = "/erro.jsp";
        }
        
        if(usuario.getId() != 0) request.getSession().setAttribute("Usuario", usuario);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }
    
}
