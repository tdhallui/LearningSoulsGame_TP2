package test;

import com.sun.istack.internal.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.CoreMatchers.instanceOf;

public class DiceTest {
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
    public void existDiceClass() {
        try {
            Class.forName("lsg.helpers.Dice");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Dice in package lsg.helpers");
        }
    }

    @Test
    public void testAttributes() {
        try {
            Class <?> c = Class.forName("lsg.helpers.Dice");
            Field f1 = c.getDeclaredField("random");
            Field f2 = c.getDeclaredField("faces");

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Dice");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attributes (random or faces)");
        }
    }

    @Test
    public void testAttributeTypes() {
        try {
            Class <?> c = Class.forName("lsg.helpers.Dice");
            Field f1 = c.getDeclaredField("random");
            Field f2 = c.getDeclaredField("faces");

            Assert.assertEquals(f1.getType(), java.util.Random.class);
            Assert.assertEquals(f2.getType(), int.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Dice");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attributes (random or faces)");
        }
    }

    @Nullable
    private Constructor<?> searchDefaultConstructor(Class<?> c) {
        for (Constructor<?> constructor : c.getConstructors()) {
            if (constructor.getParameterCount() == 1) {
                return constructor;
            }
        }
        return null;
    }

    @Test
    public void testDiceConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.helpers.Dice");
            Constructor<?> constructor = c.getDeclaredConstructor(int.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.helpers.Dice");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a constructor with one parameter for lsg.helpers.Dice class");
        }
    }

}