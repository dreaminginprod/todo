package org.home.todo.persistence;

import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.dto.NewItem;
import org.home.todo.usecases.outports.GetActiveItemsRepositoryOutPort;
import org.home.todo.usecases.outports.GetItemOutPort;
import org.home.todo.usecases.outports.PersistItemOutPort;
import org.home.todo.usecases.outports.PersistNewItemOutPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class InMemoryRepositoryAdapter implements
        GetActiveItemsRepositoryOutPort,
        GetItemOutPort,
        PersistItemOutPort,
        PersistNewItemOutPort {

    private static final System.Logger LOGGER = System.getLogger(InMemoryRepositoryAdapter.class.getName());

    private final H2Repository repository;

    InMemoryRepositoryAdapter(H2Repository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TodoItem> get(int id) {
        return repository.findById(id).map(TodoLine::to).or(Optional::empty);
    }

    @Override
    public List<TodoItem> getAll() {
        return TodoLine.to(repository.findAll());
    }

    @Override
    public TodoItem persist(TodoItem item) {
        return repository.save(TodoLine.from(item)).to();
    }

    @Override
    public TodoItem persist(NewItem newItem) {
        return repository.save(TodoLine.from(newItem)).to();
    }
}
