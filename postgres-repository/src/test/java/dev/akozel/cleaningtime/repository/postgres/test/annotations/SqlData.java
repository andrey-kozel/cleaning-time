package dev.akozel.cleaningtime.repository.postgres.test.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value= RetentionPolicy.RUNTIME)
public @interface SqlData {

    String initialScript() default "";

}
