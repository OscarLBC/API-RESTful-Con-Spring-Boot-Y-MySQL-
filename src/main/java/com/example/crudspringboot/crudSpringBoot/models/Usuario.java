package com.example.crudspringboot.crudSpringBoot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String apellido;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    public void PrePersist(){
        createdAt = new Date();
    }
}
