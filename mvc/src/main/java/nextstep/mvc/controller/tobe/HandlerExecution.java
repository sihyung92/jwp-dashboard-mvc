package nextstep.mvc.controller.tobe;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import nextstep.mvc.view.ModelAndView;

public class HandlerExecution {

    private final Method method;
    private final Object handler;

    public HandlerExecution(Method method, Object handler) {
        this.method = method;
        this.handler = handler;
    }

    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = (ModelAndView) method.invoke(handler, request, response);

        request.getAttributeNames()
            .asIterator()
            .forEachRemaining(name -> modelAndView.addObject(name, request.getAttribute(name)));

        return modelAndView;
    }
}
