package com.mkn.todosimple.repositories;

import com.mkn.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    //buscar lista de usuarios
    /*findByUserId dentro da classe Task porém, não existe o id na classe Task
    então colocamos underscore e o id, para especificar que o id é de dentro da
    classe User
    Função do Spring.
    */
    List<Task> findByUser_Id(Long id);

    /*Query JPQL: @Param chamado id,
    @Query(value = "SELECT t (de task) FROM Task (Task maiusculo estamos referenciando o Modelo Task)
    t WHERE t.user.id (t de Task.=user. o id que está dentro) = :id")
    dois pontos para referenciar ao parametro.
    @Query(value = "SELECT t FROM Task t WHERE t.user.id = :Id")
    List<Task> findByUser_Id(@Param("Id") Long id);
    */

    /*SQL: Referenciando a task (Minuscula) o nome da tabela
    Underline para referenciar a coluna do SQL
    @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true)
    List<Task> findByUser_Id(@Param("id") Long id);
    */
}
