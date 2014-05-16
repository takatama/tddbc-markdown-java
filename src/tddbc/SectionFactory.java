package tddbc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takatama on 2014/05/16.
 */
public class SectionFactory {

    public static Section[] create(String document) {
        List<Section> sections = new ArrayList<Section>();
        SectionParser parser = new SectionParser(sections);
        String[] lines = document.split("¥n");
        for (String line: lines) {
            parser.parse(line);
        }
        parser.parse("");
        return sections.toArray(new Section[sections.size()]);
    }
}
