/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.dao.DataSource;
import br.com.mateus.teamspotify.dao.PlayListDAO;
import br.com.mateus.teamspotify.model.PlayList;
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
public class PlaylistDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/erro.jsp";
        if (request.getSession().getAttribute("Usuario") != null){
            try {
                DataSource dataSource = new DataSource();
                PlayListDAO plDAO = new PlayListDAO(dataSource);
                int id = Integer.parseInt(request.getParameter("id"));
                PlayList playlist = plDAO.readPlaylistDetailsById(id);
                if (playlist != null){
                    request.getSession().setAttribute("PlayList", playlist);
                    paginaDestino = "/playlistdetails.jsp";
                }
                else{
                    request.setAttribute("erroSTR", "Erro ao recuperar Playlist!");
                }
            } 
            catch (Exception ex) {
                request.setAttribute("erroSTR", "Erro Inesperado!");
                ex.printStackTrace();
            }
        }
        else{
            request.setAttribute("erroSTR", "Voce não está conectado!");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
