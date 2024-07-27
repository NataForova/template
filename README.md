# template service
Γεια σας και πάλι

For host grpc server add
```
127.0.0.1 grpc-server-test 
```
to /etc/hosts

Copy this project to your repository.

For creating grpc server you can customize ```TemplateGrpcServiceImpl```

Add new methods for service to file ```template.proto``` and then build the project:
```
mvn clean install
```
Override new methods in ```TemplateGrpcServiceImpl``` using ```TemplateRepository```

Don't forget replace template in source file name to your actually service name.
