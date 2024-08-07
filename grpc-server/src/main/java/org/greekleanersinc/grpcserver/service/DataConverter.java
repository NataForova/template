package org.greekleanersinc.grpcserver.service;

import org.greekleanersinc.baseservice.model.Example;
import org.greekleanersinc.grpcdataservice.ServiceData;

public class DataConverter {
    public static ServiceData convertToProto(Example template) {
        return ServiceData.newBuilder()
                .setId(template.getId())
                .setText(template.getText())
                .build();
    }

    public static Example convertToPojo(ServiceData templateData) {
        return new Example(
                templateData.getId(),
                templateData.getText()
        );
    }
}
