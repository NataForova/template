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

If you want to have both grpc client and service in one application you can also customize ```TemplateGrpcClient ```.

You can also use them separate, just create and copy this code in new project and leave only ```TemplateGrpcClient ```
after building service project import ```org.greekleanersinc.servicetemplate.TemplateServiceGrpc```

Don't forget replace template in source file name to your actually service name.
