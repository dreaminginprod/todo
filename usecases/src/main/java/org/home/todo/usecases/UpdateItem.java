package org.home.todo.usecases;

import org.home.todo.usecases.dto.UpdateItemDto;

public interface UpdateItem {
    void execute(UpdateItemDto updateItemDto);
}
