package tddbc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MarkDownConverterTest {
    MarkDownConverter sut;

    @Before
    public void setUp() {
        sut = new MarkDownConverter();
    }

    @Test
    public void テキストはp要素でラップされる() {
        assertThat(sut.convert("Hello, World"), is("<p>Hello, World</p>"));
    }

    @Test
    public void ヘッダLv1はh1要素でラップされる() {
        assertThat(sut.convert("# 見出し1"), is("<h1>見出し1</h1>"));
    }

    @Test
    public void ヘッダLv2はh2要素でラップされる() {
        assertThat(sut.convert("## 見出し2"), is("<h2>見出し2</h2>"));
    }

    @Test
    public void ヘッダLv3はh3要素でラップされる() {
        assertThat(sut.convert("### 見出し3"), is("<h3>見出し3</h3>"));
    }

    @Test
    public void ヘッダLv6はh6要素でラップされる() {
        assertThat(sut.convert("###### 見出し6"), is("<h6>見出し6</h6>"));
    }

    @Test
    public void 強調表示_はstrong要素でラップされる() {
        assertThat(sut.convert("そこを_強調_"), is("<p>そこを<strong>強調</strong></p>"));
    }

    @Test
    public void リスト表示はul_li要素でラップされる() {
        assertThat(sut.convert("- list1"), is("<ul><li>list1</li></ul>"));
    }

    @Test
    public void test() {
        String document = "p1,¥np2¥n## h2";
        String expected = "<p>p1,p2</p><h2>h2</h2>";
        assertThat(sut.convert(document), is(expected));
    }

    @Test
    public void 複数行のMarkdownテキストを一括で変換する() {
        String document = "# h1¥np1,¥np2¥n## h2¥n- list1¥n- list2¥n### h3";
        String expected = "<h1>h1</h1><p>p1,p2</p><h2>h2</h2><ul><li>list1</li><li>list2</li></ul><h3>h3</h3>";
        assertThat(sut.convert(document), is(expected));
    }

}