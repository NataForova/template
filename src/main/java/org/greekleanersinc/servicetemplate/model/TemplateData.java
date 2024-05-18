package org.greekleanersinc.servicetemplate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "template")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TemplateData {
    @Id
    private Long id;

    private String text;
}
