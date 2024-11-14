package org.home.todo.usecases.scenarios;


import jakarta.inject.Named;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.GetActiveItems;
import org.home.todo.usecases.outports.GetActiveItemsRepositoryOutPort;

import java.util.stream.Stream;

@Named
class GetActiveItemsUseCase implements GetActiveItems {

    private final GetActiveItemsRepositoryOutPort getActiveItemsRepositoryOutPort;

    GetActiveItemsUseCase(GetActiveItemsRepositoryOutPort getActiveItemsRepositoryOutPort) {
        this.getActiveItemsRepositoryOutPort = getActiveItemsRepositoryOutPort;
    }

    @Override
    public Stream<TodoItem> invoke() {
        //TODO: add real life usecase with business logic (use AI to estimate based on simular finished task)
        return getActiveItemsRepositoryOutPort.getAll().stream();
    }
}
