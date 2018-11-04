package org.psgins.sqlmirror.component;

import org.springframework.stereotype.Component;

@Component
public class FieldDecorator {

    private static final String FIELD_DECORATOR = "`";

    public String decorateFieldName(String fieldName) {
        return new StringBuilder()
                .append(FIELD_DECORATOR)
                .append(fieldName)
                .append(FIELD_DECORATOR)
                .toString();
    }

    public String decorateFieldValue(String fieldValue) {
        return fieldValue;
    }

}
