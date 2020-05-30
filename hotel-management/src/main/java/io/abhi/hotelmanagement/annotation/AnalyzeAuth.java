package io.abhi.hotelmanagement.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface AnalyzeAuth {
	public boolean enabled() default true;
	public String eventType() default "SESSION_SIGNIN";
	public String description() default "";
	public boolean isFirstTimeLogin() default false;
	public boolean loginWithFingerPrint() default false;
}

