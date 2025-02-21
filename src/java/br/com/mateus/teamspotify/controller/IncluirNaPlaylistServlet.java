/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.dao.DataSource;
import br.com.mateus.teamspotify.dao.PlayListDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mateus
 */
public class IncluirNaPlaylistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String paginaResultado = "/result.jsp";
        DataSource dataSource = null;
        try {
            int idPlaylist = Integer.parseInt(request.getParameter("idplaylist"));
            int idMusica= Integer.parseInt(request.getParameter("idmusica"));
            dataSource = new DataSource();
            PlayListDAO plDAO = new PlayListDAO(dataSource);
            if (plDAO.createMusicaPlaylist(idPlaylist, idMusica)){
                request.setAttribute("strRESULT", "OK");
            }      
            dataSource.getConnection().close();
        } 
        catch (Exception ex) {
            if (dataSource != null){
                try{
                dataSource.getConnection().close();
                }catch(SQLException ex2){
                    System.out.println("Não fechei a conexao");
                }
            }
            System.out.println("Erro ao inserir "+ex.getMessage());
            request.setAttribute("strRESULT", "ERRO");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaResultado);
        dispatcher.forward(request, response);
    }   
}
