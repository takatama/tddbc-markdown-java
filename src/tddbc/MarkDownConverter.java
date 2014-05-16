package tddbc;

/**
 * Created by takatama on 2014/05/16.
 */
public class MarkDownConverter {
    public String convert(String document) {
        String converted = "";
        Section[] sections = SectionFactory.create(document);
        for (Section section: sections) {
            converted += section.convert();
        }
        return converted;
    }
}
