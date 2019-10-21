package com.yejianfengblue.spring;

import com.yejianfengblue.spring.config.AppConfig;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yejianfengblue
 */
@SpringJUnitConfig(AppConfig.class)
class SpELTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void simpleTestExpressionParserAndExpression() {

        ExpressionParser expParser = new SpelExpressionParser();
        Expression exp = expParser.parseExpression("'Hello World'");
        assertEquals("'Hello World'", exp.getExpressionString());
        assertEquals("Hello World", exp.getValue(String.class));
    }

    @Test
    void givenSpelExpressionParser_WhenParseUnmatchedParenthesis_ThenThrowParseException() {

        ExpressionParser expParser = new SpelExpressionParser();
        Assertions.assertThrows(ParseException.class, () -> {
            Expression exp = expParser.parseExpression("'abc'.substring(1,3");
        });
    }

    @Test
    void givenIllegalExpression_WhenGetValue_ThenThrowEvaluationException() {

        ExpressionParser expParser = new SpelExpressionParser();
        Expression exp = expParser.parseExpression("1");
        assertEquals(Integer.valueOf(1), exp.getValue(Integer.class));

        final Expression notIntegerExp = expParser.parseExpression("'1.1'");
        Assertions.assertThrows(EvaluationException.class, () -> notIntegerExp.getValue(Integer.class));
    }

    @Test
    void spelLiteralExpressions() {

        // The types of literal expressions supported are strings, numeric values (int, real, hex), boolean, and null
        ExpressionParser expParser = new SpelExpressionParser();
        assertEquals("Hello World",
                expParser.parseExpression("'Hello World'").getValue(String.class));
        assertEquals(Integer.valueOf(2147483647),
                expParser.parseExpression("0x7FFFFFFF").getValue(Integer.class));
        assertEquals(Boolean.TRUE,
                expParser.parseExpression("true").getValue(Boolean.class));
        assertNull(expParser.parseExpression("null").getValue());
    }

    @Test
    void spelSupportsInlineList() {

        ExpressionParser parser = new SpelExpressionParser();
        assertEquals(Arrays.asList(1, 2, 3, 4),
                parser.parseExpression("{1, 2, 3, 4}").getValue(List.class));
        List<String> abList = Arrays.asList("a", "b");
        List<String> xyList = Arrays.asList("x", "y");
        List<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(abList);
        listOfLists.add(xyList);
        assertEquals(listOfLists,
                parser.parseExpression("{{'a', 'b'}, {'x', 'y'}}").getValue(List.class));
    }

    @Test
    void spelSupportInlineMap() {

        ExpressionParser parser = new SpelExpressionParser();
        assertEquals(Map.of("A", "10", "F", "15"),
                parser.parseExpression("{'A': '10', 'F': '15'}").getValue(Map.class));
    }

    @Test
    void spelSupportsCallingMethod() {

        ExpressionParser expParser = new SpelExpressionParser();
        Expression exp = expParser.parseExpression("'Hello World'.concat('!')");
        assertEquals("Hello World!", exp.getValue(String.class));
    }

    @Test
    void spelSupportsCallingJavaBeanProperty() {

        ExpressionParser expParser = new SpelExpressionParser();
        // call getBytes()
        Expression exp = expParser.parseExpression("'Hello World'.bytes");
        assertArrayEquals("Hello World".getBytes(), (byte[]) exp.getValue());
    }

    @Test
    void spelSupportsCallingStaticClassProperty() {

        ExpressionParser expParser = new SpelExpressionParser();
        Expression exp = expParser.parseExpression(
                "T(com.yejianfengblue.spring.SpELTest.Society).Advisors");
        assertEquals("advisors", exp.getValue(String.class));
    }

    @Data
    private static class Society {

        private String name;

        public static String Advisors = "advisors";
        public static String President = "president";
    }
}