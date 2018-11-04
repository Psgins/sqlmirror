package org.psgins.sqlmirror.component.extractor;

import lombok.extern.slf4j.Slf4j;
import org.psgins.sqlmirror.model.Field;
import org.psgins.sqlmirror.model.SQLComponent;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class InsertFieldExtractor {

    public SQLComponent extract(String sql) {

        Pattern pattern = Pattern.compile("insert\\s+into\\s+(\\S+)(.*)values(.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);

        if (matcher.matches()) {
            return new SQLComponent()
                    .setTableName(matcher.group(1))
                    .setFields(extractFields(matcher.group(2), matcher.group(3)))
                    .setOriginal(sql);
        } else {
            log.warn("unable to extract insert statement {}", sql);
            throw new IllegalArgumentException();
        }
    }

    Field[] extractFields(String rawFields, String rawValues) {

        String[] fields = rawFields.replaceAll(".*\\((.*)\\).*", "$1").trim().split(",");
        String[] values = rawValues.replaceAll(".*\\((.*)\\).*", "$1").trim().split(",");

        if(fields.length == values.length) {
            Field[] extractResult = new Field[fields.length];
            for(int i=0; i< fields.length; i++) {
                extractResult[i] = new Field().setName(fields[i]).setValue(values[i]);
            }
            return extractResult;
        } else {
            log.warn("Invalid row fields {} values {}", fields, values);
            throw new IllegalArgumentException();
        }
    }

}
