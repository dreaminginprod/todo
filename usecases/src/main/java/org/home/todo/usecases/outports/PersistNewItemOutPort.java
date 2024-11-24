package org.home.todo.usecases.outports;

import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.dto.NewItem;

public interface PersistNewItemOutPort {
    TodoItem persist(NewItem newItem);
}
