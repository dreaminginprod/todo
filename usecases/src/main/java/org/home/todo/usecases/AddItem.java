package org.home.todo.usecases;

import org.home.todo.domain.NewItem;
import org.home.todo.domain.TodoItem;

public interface AddItem {
    TodoItem invoke(NewItem newItem);

}
