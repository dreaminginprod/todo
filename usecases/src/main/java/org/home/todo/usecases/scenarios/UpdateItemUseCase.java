package org.home.todo.usecases.scenarios;

import jakarta.inject.Named;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.UpdateItem;
import org.home.todo.usecases.dto.UpdateItemDto;
import org.home.todo.usecases.outports.GetItemOutPort;
import org.home.todo.usecases.outports.PersistItemOutPort;

import java.util.Optional;

@Named
class UpdateItemUseCase implements UpdateItem {

    private final System.Logger LOGGER = System.getLogger(UpdateItemUseCase.class.getName());

    private final GetItemOutPort getItemOutPort;
    private final PersistItemOutPort persistItemOutPort;

    UpdateItemUseCase(GetItemOutPort getItemOutPort, PersistItemOutPort persistItemOutPort) {
        this.getItemOutPort = getItemOutPort;
        this.persistItemOutPort = persistItemOutPort;
    }

    @Override
    public void execute(UpdateItemDto updateItemDto) {
        final Optional<TodoItem> todoItem = getItemOutPort.get(updateItemDto.id());
        todoItem.ifPresentOrElse(
                (item) -> {
                    final var updatedTodoItem = item.updateText(updateItemDto.text());
                    persistItemOutPort.persist(updatedTodoItem);
                },
                () -> {
                    LOGGER.log(System.Logger.Level.WARNING, "Todo Item now found ");

                }
        );
    }
}
