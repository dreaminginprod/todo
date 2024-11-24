package org.home.todo.usecases;

import org.home.todo.usecases.dto.NewItem;
import org.home.todo.domain.TodoItem;

public interface AddItem {
    TodoItem invoke(NewItem newItem);
}
