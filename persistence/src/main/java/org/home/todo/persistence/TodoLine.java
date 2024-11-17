package org.home.todo.persistence;

import org.home.todo.domain.TodoItem;
import org.home.todo.domain.TodoItemId;
import org.springframework.data.annotation.Id;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

record TodoLine(
        @Id Integer id,
        String item,
        LocalDateTime created,
        @Nullable LocalDateTime finishedBy
) {

    public static TodoLine from(TodoItem item) {
        return new TodoLine(
                TodoItem.getId(item.id()),
                item.item(),
                item.created(),
                item.finishedBy()
        );
    }

    public static List<TodoItem> to(Iterable<TodoLine> items) {
        final List<TodoItem> todoItems = new ArrayList<>();

        for (TodoLine item : items) {
            todoItems.add(item.to());
        }
        return todoItems;
    }

    TodoItem to() {
        return new TodoItem(
                TodoItemId.from(id()),
                item,
                created(),
                finishedBy()
        );
    }
}
