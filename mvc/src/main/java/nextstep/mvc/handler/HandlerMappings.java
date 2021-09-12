package nextstep.mvc.handler;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HandlerMappings {

    private final List<HandlerMapping> values = new ArrayList<>();

    public void add(HandlerMapping handlerMapping) {
        values.add(handlerMapping);
    }

    public void initializeAll() {
        values.forEach(HandlerMapping::initialize);
    }

    public Object getHandler(HttpServletRequest request) {
        return values.stream()
            .map(handlerMapping -> handlerMapping.getHandler(request))
            .filter(Objects::nonNull)
            .findFirst()
            .orElseThrow();
    }
}
