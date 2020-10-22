/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.model.PlayList;
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
public class PlayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/erro.jsp";
        Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
        if (usuario != null){
            PlayList playlist = (PlayList)request.getSession().getAttribute("PlayList");
            if (playlist != null){
                paginaDestino = "/player.jsp";
            }
            else{
                request.setAttribute("erroSTR", "Playlist nao encontrada!");
            }
        }
        else{
            request.setAttribute("erroSTR", "Erro! Usuario nao conectado!");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
