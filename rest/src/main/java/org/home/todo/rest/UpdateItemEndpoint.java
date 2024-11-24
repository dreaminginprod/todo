package org.home.todo.rest;

import org.home.todo.usecases.UpdateItem;
import org.home.todo.usecases.dto.UpdateItemDto;
import org.home.todo.wrapper.IdsMappingProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@RestController
class UpdateItemEndpoint {

    private final UpdateItem updateItem;
    private final IdsMappingProvider idsMappingProvider;

    UpdateItemEndpoint(UpdateItem updateItem, IdsMappingProvider idsMappingProvider) {
        this.updateItem = updateItem;
        this.idsMappingProvider = idsMappingProvider;
    }

    @PutMapping(ItemsEndpointHelper.V_1_API_ITEMS)
    ResponseEntity<Void> update(@RequestBody UpdateItemRequestBody updateItemRequestBody) {
        final var updateItemDto = updateItemRequestBody.to(idsMappingProvider::decode);
        updateItem.execute(updateItemDto);

        return ResponseEntity.noContent().build();
    }

    private record UpdateItemRequestBody(
            String id,
            String text
    ) {
        public UpdateItemDto to(Function<String, Integer> decoder) {
            //add Illegal case if we received null
            final var id = decoder.apply(id());
            return new UpdateItemDto(id, text());
        }
    }
}
