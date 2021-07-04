import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import parse.Parser;
import parse.UrlConnector;

import java.io.IOException;

public class Test {

    @org.junit.Test
    public void isConnected() throws IOException {
        Document documentHtml = Jsoup.connect("https://gle.com")
                .userAgent("Chrome/81.0.4044.138")
                .get();

        Assert.assertNotNull(documentHtml);
    }

    @org.junit.Test
    public void pageAsListReturned() throws IOException {
        UrlConnector connector = new UrlConnector();
        Parser parser = new Parser();
        Document document = connector.connect("https://simbirsoft.com");

        Assert.assertFalse(parser
                        .getPageList(document.text()
                        .replaceAll(">([^<]*)<", ""))
                        .isEmpty());
    }
}
