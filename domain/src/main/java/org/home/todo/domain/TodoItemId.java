package org.home.todo.domain;

import java.util.Objects;

public record TodoItemId(
        int value
) {
    public static TodoItemId from(int id) {
        return new TodoItemId(id);
    }

    public static Integer from(TodoItem item) {
        return Objects.requireNonNull(item.id(), "id cannot be null").value();
    }
}
