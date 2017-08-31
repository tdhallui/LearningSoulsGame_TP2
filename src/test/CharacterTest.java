package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CharacterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void existCharacterClass() {
        try {
            Class.forName("lsg.characters.Character");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character in package lsg.charaters");
        }
    }

    @Test
    public void existHeroClass() {
        try {
            Class.forName("lsg.characters.Hero");
            Class.forName("lsg.characters.Monster");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero in package lsg.charaters");
        }
    }

    @Test
    public void existMonsterClass() {
        try {
            Class.forName("lsg.characters.Monster");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster in package lsg.charaters");
        }
    }

    @Test
    public void existInheritance() {
        try {
            Class <?> c1 = Class.forName("lsg.characters.Character");
            Class <?> c2 = Class.forName("lsg.characters.Hero");
            Class <?> c3 = Class.forName("lsg.characters.Monster");

            Assert.assertTrue("Hero should be a superclass of Character", c1.isAssignableFrom(c2));
            Assert.assertTrue("Monster should be a superclass of Character", c1.isAssignableFrom(c3));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster in package lsg.charaters");
        }
    }
}