package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class LearningSoulsGameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream in = new ByteArrayInputStream("\n\n\n".getBytes());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(in);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(null);
    }

    @Test
    public void testMain() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50     (ALIVE)", list[0]);
            Assert.assertEquals("[ Monster ]          Studentatort         LIFE:   10      STAMINA:   10     (ALIVE)", list[1]);
            Assert.assertEquals("[ Monster ]          Monster_2            LIFE:   10      STAMINA:   10     (ALIVE)", list[2]);
            Assert.assertEquals("[ Monster ]          Monster_3            LIFE:   10      STAMINA:   10     (ALIVE)", list[3]);
            if (list.length > 4) {
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50     (ALIVE)", list[4]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:100) > 9", list[5]);
                Assert.assertEquals("", list[6]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30     (ALIVE)", list[7]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:99) > 7", list[8]);
                Assert.assertEquals("", list[9]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   10     (ALIVE)", list[10]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:98) > 3", list[11]);
                Assert.assertEquals("", list[12]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:    0     (ALIVE)", list[13]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:97) > 0", list[14]);
                Assert.assertEquals("", list[15]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:    0     (ALIVE)", list[16]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:96) > 0", list[17]);
                Assert.assertEquals("", list[18]);

                Assert.assertEquals("[ Monster ]          Monster_4            LIFE:   10      STAMINA:   10     (ALIVE)", list[19]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:100) > 5", list[20]);
                Assert.assertEquals("", list[21]);
                Assert.assertEquals("[ Monster ]          Monster_4            LIFE:   10      STAMINA:    0     (ALIVE)", list[22]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:99) > 0", list[23]);
                Assert.assertEquals("", list[24]);
                Assert.assertEquals("[ Monster ]          Monster_4            LIFE:   10      STAMINA:    0     (ALIVE)", list[25]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:98) > 0", list[26]);
                Assert.assertEquals("", list[27]);
                Assert.assertEquals("[ Monster ]          Monster_4            LIFE:   10      STAMINA:    0     (ALIVE)", list[28]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:97) > 0", list[29]);
                Assert.assertEquals("", list[30]);
                Assert.assertEquals("[ Monster ]          Monster_4            LIFE:   10      STAMINA:    0     (ALIVE)", list[31]);
                Assert.assertEquals("attaque avec Basic Sword (min:5 max:10 stam:20 dur:96) > 0", list[32]);
                Assert.assertEquals("", list[33]);
            }
            if (list.length > 34) {
                Assert.assertEquals("[ Hero ]             Rick                 LIFE:  100      STAMINA:   50     (ALIVE)", list[34]);
                Assert.assertEquals("[ Monster ]          Zombie               LIFE:   10      STAMINA:   10     (ALIVE)", list[35]);
                Assert.assertEquals("", list[36]);
                Assert.assertEquals(" !!! Rick attack Zombie with ShotGun (19) !!! -> Effective DMG: 00010 PV", list[37]);
                Assert.assertEquals("", list[38]);
                Assert.assertEquals("[ Hero ]             Rick                 LIFE:  100      STAMINA:   45     (ALIVE)", list[39]);
                Assert.assertEquals("[ Monster ]          Zombie               LIFE:    0      STAMINA:   10     (DEAD)", list[40]);
            }
            if (list.length > 41) {
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50     (ALIVE)", list[41]);
                Assert.assertEquals("[ Monster ]          Monster_6            LIFE:   10      STAMINA:   10     (ALIVE)", list[42]);
                Assert.assertEquals("", list[43]);
                Assert.assertEquals("Hit enter key for next move > ", list[44]);
                Assert.assertEquals("Gregooninator attacks Monster_6 with Basic Sword (ATTACK:9 | DMG : 9)", list[45]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30     (ALIVE)", list[46]);
                Assert.assertEquals("[ Monster ]          Monster_6            LIFE:    1      STAMINA:   10     (ALIVE)", list[47]);
                Assert.assertEquals("", list[48]);
                Assert.assertEquals("Hit enter key for next move > ", list[49]);
                Assert.assertEquals("Monster_6 attacks Gregooninator with Bloody Claw (ATTACK:146 | DMG : 100)", list[50]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    0      STAMINA:   30     (DEAD)", list[51]);
                Assert.assertEquals("[ Monster ]          Monster_6            LIFE:    1      STAMINA:    5     (ALIVE)", list[52]);
                Assert.assertEquals("", list[53]);
                Assert.assertEquals("--- Monster_6 WINS !!! ---", list[54]);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a static method called main");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAttributes() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Field f1 = c.getDeclaredField("hero");
            Field f2 = c.getDeclaredField("monster");

            Assert.assertEquals(f1.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f2.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f1.getType(), lsg.characters.Hero.class);
            Assert.assertEquals(f2.getType(), lsg.characters.Monster.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attributes (hero and monster)");
        }
    }

}