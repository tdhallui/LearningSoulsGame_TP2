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

            Assert.assertEquals(f1.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f2.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f3.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f4.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f5.getModifiers(), Modifier.PRIVATE);
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

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
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

            Assert.assertEquals(g1.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(g2.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(g3.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(g4.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(g5.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.weapons.Weapon");
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
            Assert.fail("should have a class called lsg.weapons.Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a setter for durability attribute in Weapon class");
        }
    }

    @Test
    public void existUse() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.weapons.Weapon");
            Method f = c.getDeclaredMethod("use");
            Method g = c.getDeclaredMethod("getDurability");

            Assert.assertEquals(f.getModifiers(), Modifier.PUBLIC);

            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, int.class, int.class, int.class);
            Object o = constructor.newInstance("Basic Sword", 5, 10, 20, 100);

            f.invoke(o);

            int durability = (int)(g.invoke(o));

            Assert.assertEquals(durability, 99);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.weapons.Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a use method in Weapon class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existIsBroken() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.weapons.Weapon");
            Method ib = c.getDeclaredMethod("isBroken");
            Method u = c.getDeclaredMethod("use");

            Assert.assertEquals(ib.getModifiers(), Modifier.PUBLIC);

            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, int.class, int.class, int.class);
            Object o = constructor.newInstance("Basic Sword", 5, 10, 20, 1);

            Assert.assertFalse((boolean)(ib.invoke(o)));

            u.invoke(o);

            Assert.assertTrue((boolean)(ib.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.weapons.Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a isBroken method in Weapon class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testWeaponToString() {
        try {
            Class<?> c = Class.forName("lsg.weapons.Weapon");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, int.class, int.class, int.class);
            Object o = constructor.newInstance("Basic Sword", 5, 10, 20, 100);
            Method ts = c.getMethod("toString");

            Assert.assertEquals("Basic Sword (min:5 max:10 stam:20 dur:100)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.weapons.Weapon");
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
    public void existSwordClass() {
        try {
            Class <?> c1 = Class.forName("lsg.weapons.Weapon");
            Class <?> c2 = Class.forName("lsg.weapons.Sword");
            Constructor<?> constructor = searchDefaultConstructor(c2);

            Assert.assertTrue("Sword should be a superclass of Weapon", c1.isAssignableFrom(c2));

            Object o = constructor.newInstance();
            Method ts = c2.getMethod("toString");

            Assert.assertEquals("Basic Sword (min:5 max:10 stam:20 dur:100)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Sword in package lsg.weapons");
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
    public void existShotGunClass() {
        try {
            Class <?> c1 = Class.forName("lsg.weapons.Weapon");
            Class <?> c2 = Class.forName("lsg.weapons.ShotGun");
            Constructor<?> constructor = searchDefaultConstructor(c2);

            Assert.assertTrue("ShotGun should be a superclass of Weapon", c1.isAssignableFrom(c2));

            Object o = constructor.newInstance();
            Method ts = c2.getMethod("toString");

            Assert.assertEquals("ShotGun (min:6 max:20 stam:5 dur:100)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ShotGun in package lsg.weapons");
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
    public void existClawClass() {
        try {
            Class <?> c1 = Class.forName("lsg.weapons.Weapon");
            Class <?> c2 = Class.forName("lsg.weapons.Claw");
            Constructor<?> constructor = searchDefaultConstructor(c2);

            Assert.assertTrue("Claw should be a superclass of Weapon", c1.isAssignableFrom(c2));

            Object o = constructor.newInstance();
            Method ts = c2.getMethod("toString");

            Assert.assertEquals("Bloody Claw (min:50 max:150 stam:5 dur:100)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Claw in package lsg.weapons");
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
}