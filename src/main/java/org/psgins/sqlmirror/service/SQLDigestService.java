package org.psgins.sqlmirror.service;

import lombok.extern.slf4j.Slf4j;
import org.psgins.sqlmirror.component.extractor.InsertFieldExtractor;
import org.psgins.sqlmirror.config.SQLType;
import org.psgins.sqlmirror.model.SQLComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SQLDigestService {

    @Autowired
    private InsertFieldExtractor insertFieldExtractor;

    public SQLComponent digest(String sql) {

        SQLType type = detectSQLType(sql);

        SQLComponent component = extractComponent(type, sql);
        component.setType(type);

        return component;
    }

    SQLType detectSQLType(String sql) {
        if(sql.toLowerCase().startsWith("insert"))
            return SQLType.INSERT;
        else
            return SQLType.UNKNOWN;
    }

    private SQLComponent extractComponent(SQLType type, String sql) {
        switch (type) {
            case INSERT:
                return insertFieldExtractor.extract(sql);
            case UNKNOWN:
            default:
                return null;
        }
    }

}
