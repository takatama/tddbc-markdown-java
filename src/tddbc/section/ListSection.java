package tddbc.section;

import tddbc.Section;

/**
 * Created by takatama on 2014/05/16.
 */
public class ListSection implements Section {
    final String[] lines;

    private ListSection(String[] lines) {
        this.lines = lines;
    }

    public static Section valueOf(String[] lines) {
        return new ListSection(lines);
    }

    @Override
    public String convert() {
        String value = "<ul>";
        for (String line: lines) {
            value += "<li>" + line.substring(2) + "</li>";
        }
        return value + "</ul>";
    }
}
