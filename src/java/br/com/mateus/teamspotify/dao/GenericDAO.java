/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mateus.teamspotify.dao;

import java.util.List;

/**
 *
 * @author Mateus
 */
public interface GenericDAO {
    public void create(Object o);
    public void update(Object o);
    public void delete(Object o);
    public List<Object> read(Object o);
}
