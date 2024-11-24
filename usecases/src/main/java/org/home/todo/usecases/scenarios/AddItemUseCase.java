package org.home.todo.usecases.scenarios;

import jakarta.inject.Named;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.AddItem;
import org.home.todo.usecases.dto.NewItem;
import org.home.todo.usecases.outports.PersistNewItemOutPort;

@Named
class AddItemUseCase implements AddItem {

    private final PersistNewItemOutPort persistNewItemOutPort;

    AddItemUseCase(PersistNewItemOutPort persistNewItemOutPort) {
        this.persistNewItemOutPort = persistNewItemOutPort;
    }

    @Override
    public TodoItem invoke(NewItem newItem) {
        return persistNewItemOutPort.persist(newItem);
    }
}
