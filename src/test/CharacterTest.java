package test;

import com.sun.istack.internal.Nullable;
import lsg.weapons.Weapon;
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
            Class<?> c1 = Class.forName("lsg.characters.Character");
            Class<?> c2 = Class.forName("lsg.characters.Hero");
            Class<?> c3 = Class.forName("lsg.characters.Monster");

            Assert.assertTrue("Hero should be a superclass of Character", c1.isAssignableFrom(c2));
            Assert.assertTrue("Monster should be a superclass of Character", c1.isAssignableFrom(c3));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster in package lsg.charaters");
        }
    }

    @Test
    public void testAttributes() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
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
                String str = ((String) (ts.invoke(o))).replaceAll("Monster_[0-9]\t", "");
                String str2 = ((String) (ts.invoke(o))).replaceAll("Monster_[0-9]", "");

                if (str.equals("[ Monster ]\tLIFE: 10\tSTAMINA: 10\t(ALIVE)")) {
                    Assert.assertEquals("[ Monster ]\tLIFE: 10\tSTAMINA: 10\t(ALIVE)", str);
                } else {
                    Assert.assertEquals("[ Monster ]                      LIFE:   10      STAMINA:   10     (ALIVE)", str2);
                }
            } catch (NoSuchFieldException e) {
                Assert.assertEquals("[ Monster ]\tMonster\tLIFE: 10\tSTAMINA: 10\t(ALIVE)", (String) (ts.invoke(o)));
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
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

                if (((String) (ts.invoke(o))).equals("[ Hero ]\tGregooninator\tLIFE: 100\tSTAMINA: 50\t(ALIVE)")) {
                    Assert.assertEquals("[ Hero ]\tGregooninator\tLIFE: 100\tSTAMINA: 50\t(ALIVE)", (String) (ts.invoke(o)));
                } else {
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50     (ALIVE)", (String) (ts.invoke(o)));
                }
            } catch (NoSuchMethodException e) {
                Assert.assertEquals("[ Hero ]\tGregooninator\tLIFE: 100\tSTAMINA: 50", (String) (ts.invoke(o)));
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testDiceAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Field f = c.getDeclaredField("dice");

            f.setAccessible(true);

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);

            Class<?> hc = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = searchDefaultConstructor(hc);
            Object o = constructor.newInstance();

            Assert.assertEquals(f.get(o).getClass().getSimpleName(), "Dice");

            Class<?> dc = Class.forName("lsg.helpers.Dice");
            Field ff = dc.getDeclaredField("faces");

            ff.setAccessible(true);

            Assert.assertEquals((int) (ff.get(f.get(o))), 101);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute called dice");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testAttackWith() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Method m = c.getDeclaredMethod("attackWith", Weapon.class);
            boolean found = true;
            Method m2 = null;

            try {
                m2 = c.getDeclaredMethod("attack");
            } catch(NoSuchMethodException e) {
                found = false;
            }

            m.setAccessible(true);

            Assert.assertTrue((!found && m.getModifiers() == Modifier.PUBLIC) || (m2.getModifiers() == Modifier.PUBLIC && m.getModifiers() == Modifier.PRIVATE));

            Class<?> hc = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = searchDefaultConstructor(hc);
            Object o = constructor.newInstance();

            Class<?> c2 = Class.forName("lsg.weapons.Sword");
            Constructor<?> constructor2 = searchDefaultConstructor(c2);
            Object o2 = constructor2.newInstance();

            int attack = (int) (m.invoke(o, o2));

            Assert.assertEquals(attack, 9);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called attackWith");
        } catch (IllegalAccessException e) {
            Assert.fail("illegal access to attackWith method");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testWeaponAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Field f = c.getDeclaredField("weapon");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), lsg.weapons.Weapon.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have private attribute named weapon");
        }
    }

    @Test
    public void testWeaponGetterSetter() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Method mg = c.getDeclaredMethod("getWeapon");
            Method ms = c.getDeclaredMethod("setWeapon", lsg.weapons.Weapon.class);

            Assert.assertEquals(mg.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(ms.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (Weapon) of getWeapon", mg.getReturnType() == lsg.weapons.Weapon.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have accessors for weapon attribute");
        }
    }

    @Test
    public void testAttack() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Method m = c.getDeclaredMethod("attack");

            Assert.assertTrue("wrong return type (int) of attack", m.getReturnType() == int.class);

            Method ms = c.getDeclaredMethod("setWeapon", Weapon.class);
            Class<?> hc = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = searchDefaultConstructor(hc);
            Object o = constructor.newInstance();

            Class<?> c2 = Class.forName("lsg.weapons.Sword");
            Constructor<?> constructor2 = searchDefaultConstructor(c2);
            Object o2 = constructor2.newInstance();

            ms.invoke(o, o2);

            int attack = (int) (m.invoke(o));

            Assert.assertEquals(attack, 9);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called attack");
        } catch (IllegalAccessException e) {
            Assert.fail("illegal access to attackWith method");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testGetHitWith() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Method m = c.getDeclaredMethod("getHitWith", int.class);
            Method mg = c.getDeclaredMethod("getLife");

            Assert.assertEquals(m.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (int) of getHitWith", m.getReturnType() == int.class);

            Class<?> hc = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = searchDefaultConstructor(hc);
            Object o = constructor.newInstance();

            int dmg = (int)(m.invoke(o, 10));

            Assert.assertEquals(dmg, 10);

            int life = (int)(mg.invoke(o));

            Assert.assertEquals(life, 90);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method named getHitWith");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

}