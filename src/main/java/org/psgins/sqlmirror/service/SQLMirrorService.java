package org.psgins.sqlmirror.service;

import lombok.extern.slf4j.Slf4j;
import org.psgins.sqlmirror.component.generator.SQLCommentGenerator;
import org.psgins.sqlmirror.component.generator.SQLDeleteGenerator;
import org.psgins.sqlmirror.model.SQLComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SQLMirrorService {

    @Autowired
    private SQLDeleteGenerator sqlDeleteGenerator;

    @Autowired
    private SQLCommentGenerator sqlCommentGenerator;

    public String generateSql(SQLComponent sqlComponent) {
        switch (sqlComponent.getType()) {
            case INSERT:
                return sqlDeleteGenerator.generate(sqlComponent.getTableName(), sqlComponent.getFields());
            case UNKNOWN:
                return sqlCommentGenerator.generate(sqlComponent.getOriginal());
            default:
                throw new UnsupportedOperationException();
        }
    }

}
