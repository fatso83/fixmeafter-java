# Runtime warnings on outdated code

This small util exists to easily add warnings to methods that you need to remember
to update/fix/remove at some point in the future. Think of it as executable comments with a timer :)

The following code will output a warning after December 20th 2023:

```java
@FixMeAfter(when = "2023-12-20", why = "This is a temporary workaround that should only live for 3 weeks until live with the real fix")
public void temporaryFeature() {
    externalService.pushLogsToElastic("used temporary feature");
}
```
Since we are using the SLF4J logging facade you can use whatever logging framework you want to see the following:
```
[main] WARN FIXME - After 2023-12-20 'io.github.fatso83.fixme.Main.Foo.temporaryFeature' needs to be looked into! This is a temporary workaround that should only live for 3 weeks until live with the real fix
```

## TODO: Need to build an agent to be able to distribute
We need to build a jar to make this usable in external libraries. See [this Baeldung example for how](https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-jvm/pom.xml) 
