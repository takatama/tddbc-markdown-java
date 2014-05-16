package tddbc.section;

import tddbc.Section;

import java.util.regex.Pattern;

/**
 * Created by takatama on 2014/05/16.
 */
public class ParagraphSection implements Section {
    String[] lines;

    private ParagraphSection(String[] lines) {
        this.lines = lines;
    }
    public static Section valueOf(String[] lines) {
        return new ParagraphSection(lines);
    }

    private String transformStrong(String line) {
        Pattern pattern = Pattern.compile("_(.+?)_");
        return pattern.matcher(line).replaceAll("<strong>$1</strong>");
    }

    @Override
    public String convert() {
        String value = "<p>";
        for (String line: lines) {
            value += transformStrong(line);
        }
        return value + "</p>";
    }
}
