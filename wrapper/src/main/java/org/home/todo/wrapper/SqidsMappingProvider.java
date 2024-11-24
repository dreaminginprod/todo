package org.home.todo.wrapper;

import org.springframework.stereotype.Component;
import org.sqids.Sqids;

import javax.annotation.CheckForNull;
import java.util.Collections;

@Component
class SqidsMappingProvider implements IdsMappingProvider{

    //TODO: use property instead of hardcoded
    private static final String ALPHABET = "FxnXM1kBN6cuhsAvjW3Co7l2RePyY8DwaU04Tzt9fHQrqSVKdpimLGIJOgb5ZE";
    public static final int MIN_LENGTH = 7;

    private final Sqids sqids;

    public SqidsMappingProvider() {
        sqids = Sqids.builder()
                .alphabet(ALPHABET)
                .minLength(MIN_LENGTH)
                .build();
    }

    @Override
    public String encode(Integer id) {
        return sqids.encode(Collections.singletonList(id.longValue()));
    }

    @CheckForNull
    @Override
    public Integer decode(String wrappedId) {
        final var decode = sqids.decode(wrappedId);
        return decode.isEmpty() ? null : decode.getFirst().intValue();
    }
}
