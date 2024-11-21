package io.github.fatso83.fixme;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class FixMeAfterAgent {
    // Notes
    // premain – will statically load the agent using -javaagent parameter at JVM startup
    // agentmain – will dynamically load the agent into the JVM using the Java Attach API. Not always supported
    private static final Logger LOGGER = LoggerFactory.getLogger(FixMeAfterAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.debug("[FixMeAfterAgent] In premain method");
        transform(inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        LOGGER.debug("[FixMeAfterAgent] In agentmain method");
        transform(inst);
    }

    private static ResettableClassFileTransformer transform(Instrumentation inst) {
        return new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform((builder, type, classLoader, module, _unused) ->
                        builder.method(ElementMatchers.isAnnotatedWith(FixMeAfter.class))
                                .intercept(Advice.to(FixMeAfterAdvice.class)))
                .installOn(inst);
    }
}
