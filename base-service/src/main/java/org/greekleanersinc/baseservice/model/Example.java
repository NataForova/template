package org.greekleanersinc.baseservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.greekleanersinc.model.BaseData;

@Entity
@Table(name = "example")
public class Example extends BaseData {
    public Example(Long id, String name) {
        super(id, name);
    }
    public Example() {

    }
}
