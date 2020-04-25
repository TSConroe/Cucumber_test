package utils;

import java.net.URI;
import java.net.URISyntaxException;

public class Utils {

    public static String getDomainName(String url)  {
        URI uri = null;

        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.split("www.")[1] : domain;
    }
}
