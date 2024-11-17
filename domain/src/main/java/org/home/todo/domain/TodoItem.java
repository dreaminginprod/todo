package org.home.todo.domain;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;

public record TodoItem(
        @Nullable TodoItemId id, // demo
        String item, // didn't use wrapper (Value Object)
        LocalDateTime created,
        @Nullable LocalDateTime finishedBy// ?
) {

    public static TodoItem from(String item) {
        return new TodoItem(null, item, LocalDateTime.now(), null);
    }

    @CheckForNull
    public static Integer getId(TodoItemId id) {
        return Objects.nonNull(id) ? id.value() : null;
    }
}
