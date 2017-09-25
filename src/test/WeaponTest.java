package test;

import com.sun.istack.internal.Nullable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

public class WeaponTest {
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
    public void existWeaponClass() {
        try {
            Class.forName("lsg.weapons.Weapon");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Weapon in package lsg.weapons");
        }
    }

    @Test
    public void testAttributes() {
        try {
            Class<?> c = Class.forName("lsg.weapons.Weapon");
            Field f1 = c.getDeclaredField("name");
            Field f2 = c.getDeclaredField("minDamage");
            Field f3 = c.getDeclaredField("maxDamage");
            Field f4 = c.getDeclaredField("stamCost");
            Field f5 = c.getDeclaredField("durability");

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Weapon");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attributes (name, minDamage, maxDamage, stamCost or durability)");
        }
    }

    @Test
    public void testAttributeTypes() {
        try {
            Class<?> c = Class.forName("lsg.weapons.Weapon");
            Field f1 = c.getDeclaredField("name");
            Field f2 = c.getDeclaredField("minDamage");
            Field f3 = c.getDeclaredField("maxDamage");
            Field f4 = c.getDeclaredField("stamCost");
            Field f5 = c.getDeclaredField("durability");

            Assert.assertEquals(f1.getType(), java.lang.String.class);
            Assert.assertEquals(f2.getType(), int.class);
            Assert.assertEquals(f3.getType(), int.class);
            Assert.assertEquals(f4.getType(), int.class);
            Assert.assertEquals(f5.getType(), int.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Weapon");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attributes (name, minDamage, maxDamage, stamCost or durability)");
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
    public void testWeaponConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.weapons.Weapon");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, int.class, int.class, int.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.weapons.Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a constructor with five parameters for lsg.weapons.Weapon class");
        }
    }

    @Test
    public void existGetters() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.weapons.Weapon");
            Method g1 = c.getDeclaredMethod("getName");
            Method g2 = c.getDeclaredMethod("getMinDamage");
            Method g3 = c.getDeclaredMethod("getMaxDamage");
            Method g4 = c.getDeclaredMethod("getStamCost");
            Method g5 = c.getDeclaredMethod("getDurability");

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have getters for all attributes in Weapon class");
        }
    }

    @Test
    public void existSetDurability() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.weapons.Weapon");
            Method f = c.getDeclaredMethod("setDurability", int.class);

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a setter for durability attribute in Weapon class");
        }
    }
}