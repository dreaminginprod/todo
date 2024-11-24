package org.home.todo.rest;

import org.home.todo.usecases.dto.NewItem;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.AddItem;
import org.home.todo.wrapper.IdsMappingProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AddItemEndpoint {

    private final AddItem addItem;

    private final IdsMappingProvider idsMappingProvider;

    AddItemEndpoint(AddItem addItem, IdsMappingProvider idsMappingProvider) {
        this.addItem = addItem;
        this.idsMappingProvider = idsMappingProvider;
    }

    @PostMapping(ItemsEndpointHelper.V_1_API_ITEMS)
    ResponseEntity<ItemResponseWebModel> addItem(@RequestBody ItemRequestWebModel itemRequestWebModel) {
        final TodoItem saved = addItem.invoke(itemRequestWebModel.to());

        return ResponseEntity.ok(
                ItemResponseWebModel.from(saved, idsMappingProvider::encode)
        );
    }

    private record ItemRequestWebModel(String text) {
        public NewItem to() {
            return new NewItem(text());//todo show validation example
        }
    }


}
