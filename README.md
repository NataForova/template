# template service
Γεια σας και πάλι

Follow next steps for creating grpc-archetype from this project:
```
 mvn clean install
```
run archetype creating command

```
 mvn clean install -Pcreate-archetype
```

Your archetype project is ready and located in ```target/generated-sources/archetype```

It is possible to copy archetype project in individual repository or create new project from generated archetype as is.

Before creating new project from archetype add your modules structure to root pom.xml ```(target/generated-sources/archetype/src/main/resources/archetype-resources/pom.xml)```


For this project structure 
```
 <modules>
    <module>communication-interface</module>        
    <module>base-service</module>
    <module>grpc-server</module>
    <module>grpc-client</module>
  </modules>
  
```

Build archetype 

```
mvn clean install
```

for generate new project use

```
 cd your_new_project
 
mvn archetype:generate \
  -DarchetypeGroupId=org.greekleanersinc \
  -DarchetypeArtifactId=grpc-archetype \
  -DarchetypeVersion=0.0.1-SNAPSHOT \
  -DgroupId=org.greekleanersinc \
  -DartifactId=test-project \
  -Dversion=0.0.1-SNAPSHOT \
  -DinteractiveMode=false
```


For host grpc server add
```
127.0.0.1 grpc-server-test 
```
to /etc/hosts