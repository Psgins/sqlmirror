package org.psgins.sqlmirror.component.generator;

import org.psgins.sqlmirror.component.FieldDecorator;
import org.psgins.sqlmirror.model.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SQLDeleteGenerator {

    static final String PREFIX = "DELETE FROM ";
    static final String WHERE_CAUSE = " WHERE ";

    @Autowired
    private FieldDecorator fieldDecorator;

    public String generate(String tableName, Field[] fields) {
        return new StringBuilder()
                .append(PREFIX)
                .append(tableName)
                .append(WHERE_CAUSE)
                .append(integrateFields(fields))
                .append(";")
                .toString();
    }

    String integrateFields(Field[] fields) {
        return Stream.of(fields)
                .map(field -> new StringBuilder()
                    .append(fieldDecorator.decorateFieldName(field.getName()))
                    .append(" = ")
                    .append(fieldDecorator.decorateFieldValue(field.getValue()))
                    .toString())
                .collect(Collectors.joining(" AND "));
    }

}
