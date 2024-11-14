package org.home.todo.usecases.scenarios;

import jakarta.inject.Named;
import org.home.todo.domain.NewItem;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.AddItem;
import org.home.todo.usecases.outports.GetNextItemIdOutPort;
import org.home.todo.usecases.outports.PersistNewItemOutPort;

@Named
class AddItemUseCase implements AddItem {

    private final PersistNewItemOutPort persistNewItemOutPort;
    private final GetNextItemIdOutPort getNextItemIdOutPort;

    AddItemUseCase(PersistNewItemOutPort persistNewItemOutPort, GetNextItemIdOutPort getNextItemIdOutPort) {
        this.persistNewItemOutPort = persistNewItemOutPort;
        this.getNextItemIdOutPort = getNextItemIdOutPort;
    }

    @Override
    public TodoItem invoke(NewItem newItem) {
        var item = TodoItem.from(getNextItemIdOutPort::next, newItem.text());
        persistNewItemOutPort.persist(item);
        return item;
    }
}
