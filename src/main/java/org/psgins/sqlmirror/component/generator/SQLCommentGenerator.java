package org.psgins.sqlmirror.component.generator;

import org.springframework.stereotype.Component;

@Component
public class SQLCommentGenerator {

    public String generate(String originalSQL) {
        return new StringBuilder().append("-- ").append(originalSQL).toString();
    }

}
