/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.dao.DataSource;
import br.com.mateus.teamspotify.dao.PlayListDAO;
import br.com.mateus.teamspotify.model.PlayList;
import br.com.mateus.teamspotify.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateus
 */
public class EfetivaPlaylistServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/home.html";
        try{
            Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
            if (usuario != null){
                String titulo = request.getParameter("txtNomePlaylist");
                PlayList p = new PlayList();
                p.setTitulo(titulo);
                p.setUsuario(usuario);
                
                DataSource dataSource = new DataSource();
                PlayListDAO plDAO = new PlayListDAO(dataSource);
                plDAO.create(p);
                dataSource.getConnection().close();
                if (usuario.getPlaylist() == null){
                    usuario.setPlaylist(new ArrayList<PlayList>());
                }
                usuario.getPlaylist().add(p);
                request.getSession().setAttribute("Usuario", usuario);
                paginaDestino = "/myplaylists.jsp";
                
            }
        }
        catch(Exception ex){
            System.out.println("Erro ao cadastrar Playlist");
            request.setAttribute("erroSTR", "Erro grave ao criar Playlist");
            paginaDestino = "/erro.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
