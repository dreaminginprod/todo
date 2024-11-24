package org.home.todo.usecases.dto;

public record UpdateItemDto(
        int id,
        String text
) {
}
