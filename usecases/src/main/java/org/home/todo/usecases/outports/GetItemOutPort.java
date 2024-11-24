package org.home.todo.usecases.outports;

import org.home.todo.domain.TodoItem;

import java.util.Optional;

public interface GetItemOutPort {
    Optional<TodoItem> get(int id);
}
