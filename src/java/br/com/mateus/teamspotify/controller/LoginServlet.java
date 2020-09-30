/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.model.Usuario;
import br.mateus.teamspotify.dao.DataSource;
import br.mateus.teamspotify.dao.UsuarioDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateus
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("txtEmail");
        String senha = request.getParameter("txtSenha");
        Usuario incompleto = new Usuario();
        incompleto.setEmail(email);
        incompleto.setSenha(senha);
        String pagina = "/erro.jsp";
                
        try {
            DataSource ds = new DataSource();
            UsuarioDAO userDAO = new UsuarioDAO(ds);
            List<Object> res = userDAO.read(incompleto);
            if (res != null && res.size() > 0){
                pagina = "/myaccount.jsp";
                request.getSession().setAttribute("Usuario", res.get(0));
            }
            else{
                request.setAttribute("erroSTR", "Usuario / Senha Invalidos");
            }
            ds.getConnection().close();
        } 
        catch (Exception ex) {
            request.setAttribute("erroSTR", "Erro ao recuperar");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
        
    }
}
