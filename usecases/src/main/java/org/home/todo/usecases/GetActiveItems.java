package org.home.todo.usecases;

import org.home.todo.domain.TodoItem;

import java.util.stream.Stream;

public interface GetActiveItems {
    Stream<TodoItem> invoke();
}
