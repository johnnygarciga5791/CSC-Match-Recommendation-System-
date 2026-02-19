package cscmatchtest;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Test20 extends TestCore {
    private Method getName;
    private Method getScore;
    private Method getOtherMbr;
    private Method compatibilityCompareTo;
    private boolean getOtherMbrFailed = false;

    public static void main(String[] args) {
        new Test20().runTests();
    }

    public void runTests() {
        new Test19().runTests();
        startTests();
        try {
            testCompatibility();
        } catch (TestExit e) {
        }
        enforceStep1Restrictions(true);
        summarizeTests();
    }

    private static String expectedSign(int value) {
        return value < 0 ? "negative" : value == 0 ? "0" : "positive";
    }

    public void testCompatibility() {
        TestClass tc = compatibilityClass;
        Method addInterest;
        Object mbrA;
        Object mbrB;
        Object mbrC;
        Object mbrF;
        Object mbrX1;
        Object mbrX2;
        Object mbrX3;
        Object mbrX4;

        requiredClass(tc);
        requiredVariable(tc, "score", Modifier.PRIVATE, "int");
        requiredVariable(tc, "otherMbr", Modifier.PRIVATE, "cscmatch.Member");
        getScore = requiredMethod(compatibilityClass, "getScore", Modifier.PUBLIC, "int");
        getOtherMbr = requiredMethod(compatibilityClass, "getOtherMbr", Modifier.PUBLIC, "cscmatch.Member");
        compatibilityCompareTo = requiredMethod(compatibilityClass, "compareTo", Modifier.PUBLIC, "int",
                compatibilityClass.getClazz());
        requiredInterface(tc, "java.lang.Comparable<cscmatch.Compatibility>");

        requiredClass(memberClass);
        requiredClass(interestClass);
        addInterest = requiredMethod(memberClass, "addInterest", Modifier.PUBLIC, "cscmatch.Interest", String.class,
                int.class);
        getName = requiredMethod(memberClass, "getName", Modifier.PUBLIC, "String");

        try {
            mbrA = memberClass.newInstance("A", 1);
            mbrB = memberClass.newInstance("B", 2);
            mbrC = memberClass.newInstance("C", 3);
            mbrF = memberClass.newInstance("F", 1);

            addInterest.invoke(mbrA, "T1", 2);
            addInterest.invoke(mbrA, "T2", 3);
            addInterest.invoke(mbrA, "T3", 5);

            addInterest.invoke(mbrB, "T1", 5);
            addInterest.invoke(mbrB, "T3", 3);
            addInterest.invoke(mbrB, "T4", 7);
            addInterest.invoke(mbrB, "T5", 1);
            addInterest.invoke(mbrB, "T6", 9);

            addInterest.invoke(mbrC, "T1", 5);
            addInterest.invoke(mbrC, "T2", 10);

            addInterest.invoke(mbrF, "T3", 8);

            testScore(mbrA, mbrB, 32);
            testScore(mbrA, mbrC, 40);
            testScore(mbrA, mbrF, 40);
            testScore(mbrB, mbrA, 26);
            testScore(mbrB, mbrC, 30);
            testScore(mbrB, mbrF, 24);
            testScore(mbrC, mbrA, 42);
            testScore(mbrC, mbrB, 33);
            testScore(mbrC, mbrF, 4);
            testScore(mbrF, mbrA, 42);
            testScore(mbrF, mbrB, 33);
            testScore(mbrF, mbrC, 7);

            testCompareTo(mbrA, mbrB, mbrC, 1);
            testCompareTo(mbrA, mbrC, mbrB, -1);
            testCompareTo(mbrC, mbrA, mbrB, -1);
            testCompareTo(mbrC, mbrB, mbrA, 1);
            testCompareTo(mbrA, mbrC, mbrF, -1);
            testCompareTo(mbrA, mbrF, mbrC, 1);
            testCompareTo(mbrA, mbrA, mbrA, 0);

            mbrX1 = memberClass.newInstance("X1", 1);
            mbrX4 = memberClass.newInstance("X4", 1);
            mbrX2 = memberClass.newInstance("X2", 2);
            mbrX3 = memberClass.newInstance("X3", 2);

            Object[] mbrs = { mbrX1, mbrX2, mbrX3, mbrX4 };

            for (int i = 0; i < mbrs.length; i++) {
                for (int j = 0; j < mbrs.length; j++) {
                    for (int k = mbrs.length - 1; k >= 0; k--) {
                        testCompareTo(mbrs[i], mbrs[j], mbrs[k], j - k);
                    }
                }
            }
        } catch (Exception e) {
            unexpected(e);
        }
    }

    public void testCompareTo(Object mbr1, Object mbr2, Object mbr3, int expected) throws Exception {
        Object cAB = compatibilityClass.newInstance(mbr1, mbr2);
        Object cAC = compatibilityClass.newInstance(mbr1, mbr3);
        int sAB = (int) getScore.invoke(cAB);
        int sAC = (int) getScore.invoke(cAC);
        int actual = (int) compatibilityCompareTo.invoke(cAB, cAC);
        if (Integer.signum(actual) != Integer.signum(expected)) {
            fail("Compatibility.compareTo from " + getName.invoke(mbr1) + " to " + getName.invoke(mbr2) + " score "
                    + sAB + " and to " + getName.invoke(mbr3) + " score " + sAC + ", got " + actual + ", should be "
                    + expectedSign(expected));
        }
    }

    public void testScore(Object my, Object other, int expected) {
        try {
            Object c = compatibilityClass.newInstance(my, other);
            Object g = getOtherMbr.invoke(c);
            if (g != other && !getOtherMbrFailed) {
                getOtherMbrFailed = true;
                if (g == null) {
                    fail("getOtherMbr returned null; must  return value provided to constructor");
                } else {
                    fail("getOtherMbr did not return value provided to constructor");
                }
            }
            int actual = (int) getScore.invoke(c);
            if (actual != expected) {
                fail("score from " + getName.invoke(my) + " to " + getName.invoke(other) + " expected " + expected
                        + " got " + actual);
            } else {
                pass("score from " + getName.invoke(my) + " to " + getName.invoke(other) + " correct at " + actual);
            }
        } catch (TestExit e) {
            // ignore failure exit request
        } catch (Exception e) {
            try {
                unexpected(e);
            } catch (TestExit e2) {
                // ignore failure exit request
            }
        }
    }
}
