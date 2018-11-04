package org.psgins.sqlmirror.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Field {
    private String type = "string";
    private String name;
    private String value;
}
