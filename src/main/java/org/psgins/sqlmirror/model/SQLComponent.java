package org.psgins.sqlmirror.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.psgins.sqlmirror.config.SQLType;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class SQLComponent {
    SQLType type;
    String tableName;
    Field[] fields;
    String original;
}
