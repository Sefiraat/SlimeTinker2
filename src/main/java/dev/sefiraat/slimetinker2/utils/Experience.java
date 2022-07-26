package dev.sefiraat.slimetinker2.utils;

import dev.sefiraat.sefilib.string.Theme;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public final class Experience {

    public static final int BARS = 60;
    public static final int EXP_LEVEL_BASE = 100;
    public static final double EXP_GROWTH = 1.3;

    private Experience() {
        throw new IllegalStateException("Utility class");
    }

    private static final Map<Integer, Integer> experienceTable = new HashMap<>();

    static {
        for (int i = 0; i < 99; i++) {
            experienceTable.put(i, ((int) (EXP_LEVEL_BASE * Math.pow(EXP_GROWTH, i))));
        }
    }

    public static boolean canLevel(int currentLevel) {
        return experienceTable.containsKey(currentLevel);
    }

    public static int getRequiredExp(int forLevel) {
        return experienceTable.getOrDefault(forLevel, 0);
    }

    @Nonnull
    public static String getExpProgressionLine(int currentExp, int requiredExp) {
        double progress = (double) currentExp / requiredExp;
        int completedMarks = (int) (BARS * progress);
        return Theme.SUCCESS.apply("|").repeat(completedMarks) +
            Theme.ERROR.apply("|").repeat(BARS - completedMarks);
    }
}
