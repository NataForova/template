package org.greekleanersinc.servicetemplate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.greekleanersinc.model.BaseTemplateData;

@Entity
@Table(name = "template")
public class TemplateData extends BaseTemplateData {
    public TemplateData(Long id, String name) {
        super(id, name);
    }
    public TemplateData() {

    }
}
