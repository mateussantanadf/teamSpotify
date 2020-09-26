/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateus
 */
public class MyPlaylistsServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaRetorno;
        Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
        if (usuario == null){
            paginaRetorno = "/index.html";           
        }
        else{
            paginaRetorno = "/myplaylists.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaRetorno);
        dispatcher .forward(request, response);
    }

}
