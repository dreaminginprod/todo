package org.home.todo.rest;

import org.home.todo.domain.NewItem;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.AddItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AddItemEndpoint {

    private final AddItem addItem;

    AddItemEndpoint(AddItem addItem) {
        this.addItem = addItem;
    }

    @PostMapping("v1/api/items")
    ResponseEntity<ItemResponseWebModel> addItem(@RequestBody ItemRequestWebModel itemRequestWebModel) {
        final var newItem = itemRequestWebModel.to();
        final var item = addItem.invoke(newItem);

        return ResponseEntity.ok(ItemResponseWebModel.from(item));
    }

    private record ItemRequestWebModel(String text) {
        public NewItem to() {
            return new NewItem(text());//todo show validation example

        }
    }

    private record ItemResponseWebModel(
            int id,
            String item
    ) {
        public static ItemResponseWebModel from(TodoItem item) {
            return new ItemResponseWebModel(item.id().value(), item.item());
        }

    }


}
