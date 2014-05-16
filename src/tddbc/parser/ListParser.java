package tddbc.parser;

import tddbc.Parser;
import tddbc.Section;
import tddbc.SectionParser;
import tddbc.section.HeadingSection;
import tddbc.section.ListSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takatama on 2014/05/16.
 */
public class ListParser implements Parser {
    SectionParser parent;
    List<Section> sections;
    List<String> lines = new ArrayList<String>();

    public ListParser(SectionParser parent, List<Section> sections) {
        this.parent = parent;
        this.sections = sections;
    }

    private void handleHeading(String line) {
        sections.add(ListSection.valueOf(lines.toArray(new String[lines.size()])));
        String[] theLine = {line};
        sections.add(HeadingSection.valueOf(theLine));
        lines = new ArrayList<String>();
        parent.toInitial();
    }

    private void handleTerminal(String line) {
        if (lines.size() > 0) {
            sections.add(ListSection.valueOf(lines.toArray(new String[lines.size()])));
            lines = new ArrayList<String>();
            parent.toInitial();
        }
    }

    private void handleList(String line) {
        lines.add(line);
    }

    private void handleDefault(String line) {
        sections.add(ListSection.valueOf(lines.toArray(new String[lines.size()])));
        lines = new ArrayList<String>();
        parent.toInitial();
    }

    @Override
    public void parse(String line) {
        if (parent.isHeading(line)) {
            handleHeading(line);
        } else if (parent.isTerminal(line)) {
            handleTerminal(line);
        } else if (parent.isList(line)) {
            handleList(line);
        } else {
            handleDefault(line);
        }
    }
}
