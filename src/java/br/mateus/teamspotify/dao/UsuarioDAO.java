/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mateus.teamspotify.dao;

import br.com.mateus.teamspotify.model.Musica;
import br.com.mateus.teamspotify.model.PlayList;
import br.com.mateus.teamspotify.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class UsuarioDAO implements GenericDAO{
    public void create(Object o){
        
    }
    public List<Object> read(Object o){
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setEmail("santanamateus02@gmail.com");
        usuario.setNome("Mateus");
        usuario.setSenha("1234");
        
        ArrayList<PlayList> playlists=new ArrayList<PlayList>();
        PlayList lista1 = new PlayList();
        lista1.setId(1);
        lista1.setTitulo("Classicos");
        ArrayList<Musica> musicaP1 = new ArrayList<Musica>();
        Musica m1 = new Musica();
        m1.setId(1);
        m1.setArtista("Iron Maiden");
        m1.setTitulo("Wasted Years");
        m1.setEstilo(1);
        m1.setAlbum("Somewhere");
        m1.setLinkMP3("musicas/IronMaiden-WastedYears.mp3");
        
        Musica m2 = new Musica();
        m2.setId(2);
        m2.setArtista("Scorpions");
        m2.setTitulo("Rock You Like a Hurricane");
        m2.setEstilo(1);
        m2.setAlbum("Scorpions");
        m2.setLinkMP3("musicas/Scorpions-RYLAH.mp3");
        
        musicaP1.add(m1);
        musicaP1.add(m2);
        lista1.setMusicas(musicaP1);
        playlists.add(lista1);
        usuario.setPlaylist(playlists);
        
        ArrayList<Object> resultado = new ArrayList<Object>();
        resultado.add(usuario);
        return resultado;
    }
    public void update(Object o){
        
    }
    public void delete(Object o){
        
    }
    
}
