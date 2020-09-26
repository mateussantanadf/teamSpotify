/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.mateus.teamspotify.dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
        String pagina;
        
        //Simular uma recuperação de dados BD
        List<Object> res;
        UsuarioDAO userDAO = new UsuarioDAO();
        res = userDAO.read(null);
        
        if (email.equals("santanamateus02@gmail.com") && (senha.equals("1234"))){
            
            request.getSession().setAttribute("Usuario", res.get(0));
            
            pagina = "/myaccount.jsp";
        }
        else{
            request.setAttribute("erroSTR", "Email / Senha não encontrados!");
            pagina = "/erro.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
        
    }
}
