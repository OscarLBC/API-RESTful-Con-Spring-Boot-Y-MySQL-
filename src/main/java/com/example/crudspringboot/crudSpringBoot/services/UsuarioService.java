package com.example.crudspringboot.crudSpringBoot.services;

import com.example.crudspringboot.crudSpringBoot.models.Usuario;

import java.util.List;

public interface UsuarioService {

    public List<Usuario> findAll();
    public Usuario findById(Long id);
    public Usuario save(Usuario usuario);
    public void delete(Long id);
}
