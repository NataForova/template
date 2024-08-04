package org.greekleanersinc;

import org.greekleanersinc.servicetemplate.ServiceData;
import org.greekleanersinc.servicetemplate.model.TemplateData;

public class TemplateConverter {
    public static ServiceData convertToProto(TemplateData template) {
        return ServiceData.newBuilder()
                .setId(template.getId())
                .setText(template.getText())
                .build();
    }

    public static TemplateData convertToPojo(ServiceData templateData) {
        return new TemplateData(
                templateData.getId(),
                templateData.getText()
        );
    }
}
