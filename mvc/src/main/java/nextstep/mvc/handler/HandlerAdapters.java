package nextstep.mvc.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandlerAdapters {

    private final Set<HandlerAdapter> values = new HashSet<>();

    public HandlerAdapters(List<HandlerAdapter> handlerAdapters) {
        this.values.addAll(handlerAdapters);
    }

    public HandlerAdapter chooseProperAdapter(Object handler) {
        return values
            .stream()
            .filter(adapter -> adapter.supports(handler))
            .findAny()
            .orElseThrow();
    }
}
