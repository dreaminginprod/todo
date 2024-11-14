package org.home.todo.domain;

public record TodoItemId(
        int value
) {
    public static TodoItemId from(int id) {
        return new TodoItemId(id);
    }
}
