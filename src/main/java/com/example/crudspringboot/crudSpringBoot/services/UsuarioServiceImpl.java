package com.example.crudspringboot.crudSpringBoot.services;

import com.example.crudspringboot.crudSpringBoot.models.Usuario;
import com.example.crudspringboot.crudSpringBoot.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>)repository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public void delete(Long id) {
     repository.deleteById(id);
    }
}
