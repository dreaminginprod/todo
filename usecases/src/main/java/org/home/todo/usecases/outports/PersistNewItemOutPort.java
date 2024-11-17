package org.home.todo.usecases.outports;

import org.home.todo.domain.TodoItem;

public interface PersistNewItemOutPort {
    TodoItem persist(TodoItem item);
}
