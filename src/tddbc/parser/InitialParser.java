package tddbc.parser;

import tddbc.Parser;
import tddbc.Section;
import tddbc.SectionParser;
import tddbc.section.HeadingSection;
import tddbc.section.ParagraphSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takatama on 2014/05/16.
 */
public class InitialParser implements Parser {
    SectionParser parent;
    List<Section> sections;
    List<String> lines = new ArrayList<String>();

    public InitialParser(SectionParser parent, List<Section> sections) {
        this.parent = parent;
        this.sections = sections;
    }

    private void handleDefault(String line) {
        lines.add(line);
    }

    private void handleList(String line) {
        parent.toList();
        parent.parse(line);
    }

    private void handleTerminal() {
        if (lines.size() > 0) {
            sections.add(ParagraphSection.valueOf(lines.toArray(new String[lines.size()])));
            lines = new ArrayList<String>();
        }
    }

    private void handleHeading(String line) {
        handleTerminal();
        String[] theLine = {line};
        sections.add(HeadingSection.valueOf(theLine));
    }

    @Override
    public void parse(String line) {
        if (parent.isHeading(line)) {
            handleHeading(line);
        } else if (parent.isTerminal(line)) {
            handleTerminal();
        } else if (parent.isList(line)) {
            handleList(line);
        } else {
            handleDefault(line);
        }
    }
}
