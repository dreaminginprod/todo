package org.home.todo.usecases.outports;

import org.home.todo.domain.TodoItem;

import java.util.List;

public interface GetActiveItemsRepositoryOutPort {
    List<TodoItem> getAll();
}
