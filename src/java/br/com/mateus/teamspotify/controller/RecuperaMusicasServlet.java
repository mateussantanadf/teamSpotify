/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.dao.DataSource;
import br.com.mateus.teamspotify.dao.MusicaDAO;
import br.com.mateus.teamspotify.model.Usuario;
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
public class RecuperaMusicasServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/erro.jsp";
        try {
            Usuario usuario = (Usuario)request.getSession().getAttribute("Usuario");
            if (usuario == null){
                request.setAttribute("erroSTR", "Usuario não conectado!");
            }
            else{
                DataSource dataSource = new DataSource();
                MusicaDAO mdao = new MusicaDAO(dataSource);
                List<Object> lista = mdao.read(null);
                if (lista == null){
                    request.setAttribute("erroSTR", "Erro ao recuperar musicas do Banco de Dados");
                }
                else{
                    String idPlaylist = request.getParameter("idplaylist");
                    request.setAttribute("idPlaylist", idPlaylist);
                    request.setAttribute("ListaMusicas", lista);
                    paginaDestino = "/minhasmusicas.jsp";
                }
                dataSource.getConnection().close();
            }
        } 
        catch (Exception ex) {
            System.out.println("Erro ao montar pagina de musicas "+ex.getMessage());
            request.setAttribute("erroSTR", "Erro ao montar pagina de musicas");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
