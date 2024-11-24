package org.home.todo.persistence;

import org.home.todo.domain.TodoItem;
import org.home.todo.domain.TodoItemId;
import org.home.todo.usecases.dto.NewItem;
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

    static TodoLine from(TodoItem item) {
        return new TodoLine(
                TodoItemId.from(item),
                item.text(),
                item.created(),
                item.finishedBy()
        );
    }

    static List<TodoItem> to(Iterable<TodoLine> items) {
        final List<TodoItem> todoItems = new ArrayList<>();

        for (TodoLine item : items) {
            todoItems.add(item.to());
        }
        return todoItems;
    }

    public static TodoLine from(NewItem newItem) {
        return new TodoLine(
                null,
                newItem.text(),
                LocalDateTime.now(),
                null
        );
    }

    TodoItem to() {
        return new TodoItem(
                TodoItemId.from(id()),
                item(),
                created(),
                finishedBy()
        );
    }
}
