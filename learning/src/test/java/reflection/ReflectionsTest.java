package reflection;

import annotation.Component;
import annotation.Controller;
import annotation.Repository;
import annotation.Service;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ReflectionsTest {

    private static final Logger log = LoggerFactory.getLogger(ReflectionsTest.class);

    @Test
    void showAnnotationClass() throws Exception {
        Reflections reflections = new Reflections("examples");
        Set<Class<?>> typesAnnotatedWithController = reflections.getTypesAnnotatedWith(Controller.class);
        Set<Class<?>> typesAnnotatedWithService = reflections.getTypesAnnotatedWith(Service.class);
        Set<Class<?>> typesAnnotatedWithRepository = reflections.getTypesAnnotatedWith(Repository.class);

        println(typesAnnotatedWithController, Controller.class);
        println(typesAnnotatedWithService, Service.class);
        println(typesAnnotatedWithRepository, Repository.class);
    }

    private void println(Set<Class<?>> typesAnnotatedWith, Class<?> annotation) {
        typesAnnotatedWith.stream()
            .map(Class::getSimpleName)
            .forEach(name -> log.info("annotationType : {}, name : {}", annotation.getSimpleName(), name));
    }
}
