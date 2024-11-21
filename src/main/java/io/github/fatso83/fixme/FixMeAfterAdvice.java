package io.github.fatso83.fixme;

import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.LocalDate;

public class FixMeAfterAdvice {
    public static final Logger logger = LoggerFactory.getLogger("FIXME");

    @Advice.OnMethodEnter
    static void onEnter(@Advice.Origin Method method) {
        if (method.isAnnotationPresent(FixMeAfter.class)) {
            FixMeAfter annotation = method.getAnnotation(FixMeAfter.class);
            LocalDate expirationDate = LocalDate.parse(annotation.when());
            if (LocalDate.now().isAfter(expirationDate)) {
                logger.warn("After {} '{}.{}' needs to be looked into! {}", annotation.when(), method.getDeclaringClass().getCanonicalName(), method.getName(), annotation.why());
            }
        }
    }
}

