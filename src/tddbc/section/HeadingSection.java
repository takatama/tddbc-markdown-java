package tddbc.section;

import tddbc.Section;

/**
 * Created by takatama on 2014/05/16.
 */
public class HeadingSection implements Section {
    final String[] lines;
    final int level;

    private int getLevel(String line) {
        for(int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '#') {
                return i;
            }
        }
        return 0;
    }

    private HeadingSection(String[] lines) {
        assert(lines.length == 1);
        this.lines = lines;
        level = getLevel(lines[0]);
    }

    public static Section valueOf(String[] lines) {
        return new HeadingSection(lines);
    }

    @Override
    public String convert() {
        String value = "<h" + level + ">";
        for (String line: lines) {
            value += line.substring(level + 1);
        }
        return value + "</h" + level + ">";
    }
}
