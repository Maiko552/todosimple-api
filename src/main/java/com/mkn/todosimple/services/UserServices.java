package com.mkn.todosimple.services;

import com.mkn.todosimple.models.User;
import com.mkn.todosimple.repositories.TaskRepository;
import com.mkn.todosimple.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
public class UserServices {
    //Service se comunica com os repositories
    //precisamos do construtor porem a classe é uma interface entao seria impossivel instanciar
    //@Autowired faz a função
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id){
        //Optinal serve para nao receber dado nulo, porem traz vazio
        //não implementamos nenhum findById lá porem o jparepository implementa
        Optional<User> user = this.userRepository.findById(id);
        //retornar o usuario caso exista, se estiver vazio retorna uma excecão
        return user.orElseThrow(() -> new RuntimeException(
                "User não encontrado: " + id + " Tipo" + User.class.getName()
        ));
    }


    @Transactional
    //@Transactional persistencia de dado. Controle melhor de inserções (create, update) no banco de dados
    public User createUser(User obj){
        //Caso ele mande um obj com id, precisamos garantir que ficará null
        obj.setId(null);
        obj = this.userRepository.save(obj);
        //caso crie a conta com tasks
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }

    @Transactional
    public User updateUser(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    public void deleteUser(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }

}
