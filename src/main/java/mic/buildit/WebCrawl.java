package mic.buildit;

import java.net.URI;
import java.net.URISyntaxException;

public class WebCrawl {

    public static void main(String[] args) {
        if(args.length>0){

            String url = args[0];
            try {
                System.out.println("web crawl this domain: "  + getDomainName(url));

                WebCrawler webCrawler = new WebCrawler(url);
                webCrawler.getPageLinks(url);

            } catch (Exception e) {
                System.out.println(args[0] +" not a valid URL");
            }
        }
        else{
            System.out.println("No URL specified");
        }
    }

    public static String getDomainName(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String domain = uri.getHost();

        return domain!= null ? (domain.startsWith("www.") ? domain.substring(4) : domain) : null;
    }
}
