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
public class PlaylistsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/myplaylists.jsp";
        try {
            Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
            if (usuario != null){
                // tá logado?
                if (usuario.getPlaylist() == null){ //nao tem playlist?
                    //Recupera do banco
                    DataSource dataSource = new DataSource();
                    PlayListDAO plDAO = new PlayListDAO(dataSource);
                    List<Object> lista = plDAO.read(usuario.getId());
                    dataSource.getConnection().close();
                    
                    System.out.println("Recuperei valores!");
                    
                    if (lista != null){
                        //vou passar por cada elemento do q veio do banco e vou referenciar o usuario
                        ArrayList<PlayList> myPlaylists = new ArrayList<PlayList>();
                        for (Object o: lista){
                            PlayList novaPl = (PlayList)o;
                            novaPl.setUsuario(usuario);
                            myPlaylists.add(novaPl);
                        }
                        usuario.setPlaylist(myPlaylists);

                    }
                }
                request.getSession().setAttribute("Usuario", usuario);
                paginaDestino = "/myplaylists.jsp";
            }    
        } 
        catch (Exception ex) {
            System.out.println("Erro ao recuperar Playlists "+ex.getMessage());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
