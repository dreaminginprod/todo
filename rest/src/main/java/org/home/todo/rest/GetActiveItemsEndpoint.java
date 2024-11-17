package org.home.todo.rest;

import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.GetActiveItems;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
class GetActiveItemsEndpoint {

    private final GetActiveItems getAllItemsUseCase;

    GetActiveItemsEndpoint(GetActiveItems getAllItemsUseCase) {
        this.getAllItemsUseCase = getAllItemsUseCase;
    }

    @GetMapping("v1/api/items")
    ResponseEntity<List<ItemWebModel>> getAll() {
        final var items = getAllItemsUseCase.invoke()
                .map(ItemWebModel::from)
                .toList();
        return ResponseEntity.ok(items);
    }

    private record ItemWebModel(
            int id,
            String item
    ) {
        private static ItemWebModel from(TodoItem todoItem) {
            //the same as previous.
            return new ItemWebModel(Objects.requireNonNull(todoItem.id()).value(), todoItem.item());
        }
    }
}
