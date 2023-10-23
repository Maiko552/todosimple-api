package com.mkn.todosimple.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = User.TABLE_NAME)
public class User {

    //garantir que as anotações de coluna não sejam ignoradas
    public interface CreateUser {
    }
    public interface UpdateUser {
    }

    //Garantindo que o nome da tabela seja o mesmo do nome da classe
    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = {CreateUser.class})
    @NotEmpty(groups = {CreateUser.class})
    @Size(groups = CreateUser.class, min = 2, max = 100)
    private String username;

    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups = {CreateUser.class, UpdateUser.class})
    @Column(name = "password", length = 60, nullable = false)
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 60)
    private String password;
}
