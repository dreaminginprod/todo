package org.home.todo.rest;

import org.home.todo.domain.NewItem;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.AddItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
class AddItemEndpoint {

    private final AddItem addItem;

    AddItemEndpoint(AddItem addItem) {
        this.addItem = addItem;
    }

    @PostMapping("v1/api/items")
    ResponseEntity<ItemResponseWebModel> addItem(@RequestBody ItemRequestWebModel itemRequestWebModel) {
        final TodoItem saved = addItem.invoke(itemRequestWebModel.to()); //logic here

        //just mapping nothing more
        return ResponseEntity.ok(
                ItemResponseWebModel.from(saved)
        );
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
            // now we have problem with this nullable field good part to clarify how to avoid this sh**t
            //for now I will just double nonnull check then will be fixed with pure way
            return new ItemResponseWebModel(
                    Objects.requireNonNull(item.id()).value(),
                    item.item()
            );
        }
    }


}
