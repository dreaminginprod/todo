package org.home.todo.rest;

import org.home.todo.usecases.GetActiveItems;
import org.home.todo.wrapper.IdsMappingProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class GetActiveItemsEndpoint {

    private final GetActiveItems getAllItemsUseCase;
    private final IdsMappingProvider idsMappingProvider;

    GetActiveItemsEndpoint(GetActiveItems getAllItemsUseCase, IdsMappingProvider idsMappingProvider) {
        this.getAllItemsUseCase = getAllItemsUseCase;
        this.idsMappingProvider = idsMappingProvider;
    }

    @GetMapping(ItemsEndpointHelper.V_1_API_ITEMS)
    ResponseEntity<List<ItemResponseWebModel>> getAll() {
        final var items = getAllItemsUseCase.invoke()
                .map(item -> ItemResponseWebModel.from(item, idsMappingProvider::encode))
                .toList();
        return ResponseEntity.ok(items);
    }
}
