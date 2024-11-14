package org.home.todo.domain;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.function.Supplier;

public record TodoItem(
        TodoItemId id, // demo
        String item, // didn't use wrapper (Value Object)
        LocalDateTime created,
        @Nullable LocalDateTime finishedBy// ?
) {

    public static TodoItem from(Supplier<Integer> indexSupplier, String item) {
        var newId = TodoItemId.from(indexSupplier.get());
        return new TodoItem(newId, item, LocalDateTime.now(), null);
    }
}
