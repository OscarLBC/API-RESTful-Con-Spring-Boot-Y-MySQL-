package com.example.crudspringboot.crudSpringBoot.controllers;

import com.example.crudspringboot.crudSpringBoot.models.Usuario;
import com.example.crudspringboot.crudSpringBoot.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuarios")
    public List<Usuario> getItems(){
        return service.findAll();
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> crearItem(@RequestBody Usuario usuario){
        Usuario usuarioNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            usuarioNew= service.save(usuario);
        } catch (DataAccessException e){
          response.put("mensaje", "Error: al realizar el registro de usuario");
          response.put("error", e.getMessage());
          return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Registro Exitoso");
        response.put("Usuario",usuarioNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id ){
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();

        try {
           usuario = service.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error: al realizar el registro de usuario");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(usuario == null){
            response.put("mensaje","Usuario con la id: "+id+" no existe");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("Usuario",usuario);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

    }
    @PutMapping("usuarios/{id}")
    public ResponseEntity<?> updateItem(@RequestBody Usuario usuario,@PathVariable Long id){
        Usuario usuarioActual = service.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();

        if(usuarioActual == null){
            response.put("mensaje","Usuario con la id: "+id+" no existe");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }


        try {
            usuarioActual.setApellido(usuario.getApellido());
            usuarioActual.setNombre(usuario.getNombre());

            usuarioUpdate = service.save(usuarioActual);

        } catch (DataAccessException e){
            response.put("mensaje", "Error: al realizar la actualizacion de usuario");
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","Usuario actualizado");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

    }
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        try{
            service.delete(id);
        } catch (DataAccessException e){
            response.put("mensaje","Usuario con la id: "+id+" no existe");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("mensaje","Usuario eliminado");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);

    }

}
