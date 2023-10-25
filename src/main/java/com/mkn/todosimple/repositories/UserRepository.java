package com.mkn.todosimple.repositories;


import com.mkn.todosimple.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //JpaRepository ja tem todas as funções de findBy, poderia ser o CrudRepository mas jpa é mais completo

}
