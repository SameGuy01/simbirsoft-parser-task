package parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlConnector {
    static Logger LOGGER = Logger.getLogger(UrlConnector.class.getName());

    public Document connect(String url) throws IOException {
        LOGGER.log(Level.WARNING,"Подключаемся к "+url);

            Document documentHtml = Jsoup.connect(url)
                    .userAgent("Chrome/81.0.4044.138")
                    .get();

            LOGGER.log(Level.INFO,"Подключение выполнено успешно.");

            return documentHtml;
    }
}
