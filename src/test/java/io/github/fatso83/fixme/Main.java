package io.github.fatso83.fixme;

import net.bytebuddy.agent.ByteBuddyAgent;

public class Main {

    public static void main(String[] args) {
        final var _instrumentation = ByteBuddyAgent.install();// Install the agent - dynamically loading it (will not be allowed in JDK 22+ by default)
        FixMeAfterAgent.premain(null, _instrumentation);

        final var foo = new Foo();
        foo.temporaryFeature();
    }

    static class Foo {

        @FixMeAfter(when = "2023-12-20", why = "This is a temporary workaround that should only live for 3 weeks until live with the real fix")
        public void temporaryFeature() {
            System.out.println("Executing temporary feature");
        }
    }
}