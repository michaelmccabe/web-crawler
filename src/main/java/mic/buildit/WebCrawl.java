package mic.buildit;

import java.net.URI;
import java.net.URISyntaxException;

public class WebCrawl {

    public static void main(String[] args) {
        if(args.length>0){
            try {
                System.out.println("web crawl this domain: "  + getDomainName(args[0]));
            } catch (Exception e) {
                System.out.println(args[0] +" not a valid URL");
            }
        }
        else{
            System.out.println("No URL specified");
        }
    }

    public static String getDomainName(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
