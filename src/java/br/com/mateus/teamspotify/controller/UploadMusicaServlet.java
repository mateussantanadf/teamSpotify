/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.controller;

import br.com.mateus.teamspotify.dao.DataSource;
import br.com.mateus.teamspotify.dao.MusicaDAO;
import br.com.mateus.teamspotify.model.Musica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Mateus
 */
public class UploadMusicaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino = "/erro.jsp";
        if (request.getSession().getAttribute("Usuario") != null){
            try {
                String artista = request.getParameter("txtArtista");
                String album = request.getParameter("txtAlbum");
                String titulo = request.getParameter("txtNomeMusica");
                int estilo = Integer.parseInt(request.getParameter("txtEstilo"));
                
                InputStream arqOriginal = request.getPart("fileMP3").getInputStream();
                String nomeArquivoOriginal = request.getPart("fileMP3").getSubmittedFileName();
                String nomeArquivo = getServletContext().getRealPath("/")+"/musicas/"+request.getPart("fileMP3").getSubmittedFileName();
                FileOutputStream arquivoMP3 = new FileOutputStream(nomeArquivo);
                byte b[] = new byte[1024];
                while (arqOriginal.available()>0){
                    arqOriginal.read(b);
                    arquivoMP3.write(b);
                }
                arqOriginal.close();
                arquivoMP3.close();
                
                Musica musica = new Musica();
                musica.setAlbum(album);
                musica.setArtista(artista);
                musica.setEstilo(estilo);
                musica.setTitulo(titulo);
                musica.setLinkMP3("musicas/"+nomeArquivoOriginal);
                
                DataSource dataSource = new DataSource();
                MusicaDAO musicaDAO = new MusicaDAO(dataSource);
                musicaDAO.create(musica);
                dataSource.getConnection().close();
                
                paginaDestino = "/myaccount.jsp";
            } 
            catch (Exception ex) {
                request.setAttribute("erroSTR", "ERRO: Upload Falhou!");
            }
        }
        else{
            request.setAttribute("erroSTR", "Erro: Usuario não Conectado");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
