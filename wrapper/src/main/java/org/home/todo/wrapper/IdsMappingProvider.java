package org.home.todo.wrapper;

import javax.annotation.CheckForNull;

public interface IdsMappingProvider {
    String encode(Integer id);

    @CheckForNull
    Integer decode(String wrappedId);
}
