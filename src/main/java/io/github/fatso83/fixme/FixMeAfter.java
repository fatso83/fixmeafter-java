package io.github.fatso83.fixme;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FixMeAfter {
    /**
     * Date in "yyyy-MM-dd"
     */
    String when();

    /**
     * Required: why we should warn about this method. Example: "After June 6 we should be live with the replacement code and this code no longer serves any purpose"
     */
    String why();  // Reason for the annotation
}
