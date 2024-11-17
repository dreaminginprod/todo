package org.home.todo.persistence;

import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.outports.GetActiveItemsRepositoryOutPort;
import org.home.todo.usecases.outports.GetNextItemIdOutPort;
import org.home.todo.usecases.outports.PersistNewItemOutPort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class InMemoryRepositoryAdapter implements GetActiveItemsRepositoryOutPort, PersistNewItemOutPort, GetNextItemIdOutPort {

    private static final System.Logger LOGGER = System.getLogger(InMemoryRepositoryAdapter.class.getName());

    private  static final int FIRST_ELEMENT = 1;
    private final H2Repository repository;

    InMemoryRepositoryAdapter(H2Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<TodoItem> getAll() {
        return TodoLine.to(repository.findAll());
    }

    @Override
    public int next() {
        final Integer currentId = getAll().stream()
                .map(item -> item.id().value())
                .max(Integer::compareTo)
                .orElse(FIRST_ELEMENT);
        LOGGER.log(System.Logger.Level.INFO, "Current max id: #" + currentId);
        return 1 + currentId; //DO NOT DO LIKE THAT NEVER because it is stub
    }

    @Override
    public TodoItem persist(TodoItem item) {
        return repository.save(TodoLine.from(item)).to();
    }
}
