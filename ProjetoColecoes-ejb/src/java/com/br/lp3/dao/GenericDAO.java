package com.br.lp3.dao;

import java.util.List;

/**
 *
 * @author 1147106
 */
public interface GenericDAO<E> {
    public void insert(E e); //CREATE
    
    public List<E> findAll(); //READ
    public E findById(long id);
    
    public void modify(E e); //UPDATE
    
    public void remove(E e); //DELETE
}