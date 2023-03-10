package com.example.crudspringboot.crudSpringBoot.respositories;


import com.example.crudspringboot.crudSpringBoot.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Usuario,Long> {
}
