package org.home.todo.domain;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;

public record TodoItem(
        TodoItemId id,
        String text,
        LocalDateTime created,
        @Nullable LocalDateTime finishedBy
) {

    public TodoItem updateText(String newText) {
        return new TodoItem(
                id(),
                newText,
                created(),
                finishedBy()
        );
    }
}
