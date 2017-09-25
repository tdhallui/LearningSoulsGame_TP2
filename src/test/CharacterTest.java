package test;

import com.sun.istack.internal.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

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

    @Test
    public void testAttributes() {
        try {
            Class <?> c = Class.forName("lsg.characters.Character");
            Field f1 = c.getDeclaredField("name");
            Field f2 = c.getDeclaredField("life");
            Field f3 = c.getDeclaredField("maxLife");
            Field f4 = c.getDeclaredField("stamina");
            Field f5 = c.getDeclaredField("maxStamina");

            Assert.assertEquals(f1.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f2.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f3.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f4.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f5.getModifiers(), Modifier.PRIVATE);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attributes (name, life, maxLife, stamina or maxStamina)");
        }
    }

    @Nullable
    private Constructor<?> searchDefaultConstructor(Class<?> c) {
        for (Constructor<?> constructor : c.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }

    @Test
    public void testCharacterToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.characters.Character");
            Method ts = c.getDeclaredMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString");
        }
    }

    @Test
    public void existMonsterToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.characters.Monster");
            Method ts = c.getDeclaredMethod("toString");

            Assert.fail("should have not a method called toString in Monster class");
            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (NoSuchMethodException e) {
        }
    }

    @Test
    public void existHeroToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.characters.Hero");
            Method ts = c.getDeclaredMethod("toString");

            Assert.fail("should have not a method called toString in Hero class");
            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
        }
    }

    @Test
    public void testMonsterToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.characters.Monster");
            Constructor<?> constructor = searchDefaultConstructor(c);
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            try {
                Field f = c.getDeclaredField("INSTANCES_COUNT");
                String str = ((String)(ts.invoke(o))).replaceAll("Monster_[0-9]\t", "");
                String str2 = ((String)(ts.invoke(o))).replaceAll("Monster_[0-9]", "");

                if (str.equals("[ Monster ]\tLIFE: 10\tSTAMINA: 10\t(ALIVE)")) {
                    Assert.assertEquals("[ Monster ]\tLIFE: 10\tSTAMINA: 10\t(ALIVE)", str);
                } else {
                    Assert.assertEquals("[ Monster ]                      LIFE:   10      STAMINA:   10     (ALIVE)", str2);
                }
            } catch (NoSuchFieldException e) {
                Assert.assertEquals("[ Monster ]\tMonster\tLIFE: 10\tSTAMINA: 10\t(ALIVE)", (String)(ts.invoke(o)));
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHeroToString() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = searchDefaultConstructor(c);
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            try {
                Method m = c.getMethod("isAlive");

                if (((String)(ts.invoke(o))).equals("[ Hero ]\tGregooninator\tLIFE: 100\tSTAMINA: 50\t(ALIVE)")) {
                    Assert.assertEquals("[ Hero ]\tGregooninator\tLIFE: 100\tSTAMINA: 50\t(ALIVE)", (String) (ts.invoke(o)));
                } else {
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50     (ALIVE)", (String) (ts.invoke(o)));
                }
            } catch (NoSuchMethodException e) {
                Assert.assertEquals("[ Hero ]\tGregooninator\tLIFE: 100\tSTAMINA: 50", (String)(ts.invoke(o)));
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}