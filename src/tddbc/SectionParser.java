package tddbc;

import tddbc.parser.InitialParser;
import tddbc.parser.ListParser;

import java.util.List;

/**
 * Created by takatama on 2014/05/16.
 */
public class SectionParser {
    private Parser initial;
    private Parser list;
    private Parser parser;

    SectionParser(List<Section> sections) {
        initial = new InitialParser(this, sections);
        list = new ListParser(this, sections);
        parser = initial;
    }

    public void parse(String line) {
        parser.parse(line);
    }

    public void toInitial() {
        parser = initial;
    }

    public void toList() {
        parser = list;
    }

    public boolean isTerminal(String line) {
        return line.trim().equals("");
    }

    public boolean isHeading(String line) {
        return line.startsWith("#");
    }

    public boolean isList(String line) {
        return line.startsWith("- ");
    }
}
