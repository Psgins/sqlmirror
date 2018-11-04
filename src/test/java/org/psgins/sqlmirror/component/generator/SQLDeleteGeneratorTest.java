package org.psgins.sqlmirror.component.generator;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.psgins.sqlmirror.component.FieldDecorator;
import org.psgins.sqlmirror.model.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class SQLDeleteGeneratorTest {

    @Spy
    @InjectMocks
    private SQLDeleteGenerator subject;

    @Mock
    private FieldDecorator fieldDecorator;

    @Test
    public void generate() {

        doReturn("`val1` = 123 AND `val2` = 456").when(subject).integrateFields(any(Field[].class));

        Field[] fields = new Field[]{
                new Field()
                        .setName("val1")
                        .setValue("123"),
                new Field()
                        .setName("val2")
                        .setValue("456")
        };

        String result = subject.generate("sometable", fields);

        assertEquals("DELETE FROM sometable WHERE `val1` = 123 AND `val2` = 456;", result);
    }

    @Test
    public void integrateFields() {

        doReturn("`val1`").when(fieldDecorator).decorateFieldName(eq("val1"));
        doReturn("`val2`").when(fieldDecorator).decorateFieldName(eq("val2"));
        doReturn("123").when(fieldDecorator).decorateFieldValue(eq("123"));
        doReturn("456").when(fieldDecorator).decorateFieldValue(eq("456"));

        Field[] fields = new Field[]{
                new Field()
                        .setName("val1")
                        .setValue("123"),
                new Field()
                        .setName("val2")
                        .setValue("456")
        };

        String result = subject.integrateFields(fields);

        assertEquals("`val1` = 123 AND `val2` = 456", result);
    }
}