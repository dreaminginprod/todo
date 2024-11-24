package org.home.todo.rest;

import org.home.todo.domain.TodoItem;
import org.home.todo.domain.TodoItemId;

import java.util.Objects;
import java.util.function.Function;

record ItemResponseWebModel(
        String id,
        String item
) {
    static ItemResponseWebModel from(TodoItem item, Function<Integer, String> encoder) {
        final var id = TodoItemId.from(item);
        final var encodedId = encoder.apply(id);

        return new ItemResponseWebModel(encodedId, item.text());
    }
}
