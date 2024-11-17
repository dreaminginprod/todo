package org.home.todo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2Repository extends CrudRepository<TodoLine, Integer> {
}

