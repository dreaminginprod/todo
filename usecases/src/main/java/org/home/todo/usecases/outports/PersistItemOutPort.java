package org.home.todo.usecases.outports;

import org.home.todo.domain.TodoItem;

public interface PersistItemOutPort {
    TodoItem persist(TodoItem item);
}
