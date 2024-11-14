package org.home.todo.persistence;

import jakarta.inject.Named;
import org.home.todo.domain.TodoItem;
import org.home.todo.usecases.outports.GetActiveItemsRepositoryOutPort;
import org.home.todo.usecases.outports.GetNextItemIdOutPort;
import org.home.todo.usecases.outports.PersistNewItemOutPort;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Named
class InMemoryRepositoryAdapter implements GetActiveItemsRepositoryOutPort, PersistNewItemOutPort, GetNextItemIdOutPort {

    private final Map<Integer, TodoItem> stub = new ConcurrentHashMap<>();

    public InMemoryRepositoryAdapter() {
        AtomicInteger atomicInteger = new AtomicInteger();
        var firstStubTask = TodoItem.from(atomicInteger::incrementAndGet, "First Stub Task");
        var secondStubTask = TodoItem.from(atomicInteger::incrementAndGet, "Second Stub Task");
        stub.put(firstStubTask.id().value(), firstStubTask);
        stub.put(secondStubTask.id().value(), secondStubTask);
    }

    @Override
    public List<TodoItem> getAll() {
        return stub.values().stream()
                .filter(item -> Objects.isNull(item.finishedBy()))
                .toList();
    }

    @Override
    public int next() {
        return Collections.max(stub.keySet()) + 1;
    }

    @Override
    public void persist(TodoItem item) {
        if (Objects.nonNull(stub.put(item.id().value(), item))) {
            throw new IllegalStateException("Id was duplicated");
        }
    }
}
