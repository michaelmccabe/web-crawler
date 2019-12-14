package mic.buildit;

import mic.buildit.exceptions.WebCrawlerException;
import org.apache.commons.validator.UrlValidator;

import java.net.URI;
import java.net.URISyntaxException;

public class WebCrawl {

    public static void main(String[] args) {
        boolean saveFile=true;
        if(args.length>0){
            if(args.length>1){
                saveFile = !args[1].equalsIgnoreCase("false");
            }

            String url = args[0];
            try {
                System.out.println("web crawl this domain: "  + getDomainName(url));
                WebCrawler webCrawler = new WebCrawler(url, saveFile);
                webCrawler.getPageLinks(url);

            } catch (WebCrawlerException e) {
                System.out.println(args[0] + ": " +e.getMessage());
            }
        }
        else{
            System.out.println("No URL specified");
        }
    }

    public static String getDomainName(String url) throws WebCrawlerException {

        UrlValidator urlValidator = new UrlValidator();

        if(!urlValidator.isValid(url)){
            throw new WebCrawlerException("URL is not valid");
        }

        URI uri = null;
        try {
            uri = new URI(url);

        } catch (URISyntaxException e) {
            throw new WebCrawlerException(e.getMessage());
        }
        String domain = uri.getHost();

        return domain!= null ? (domain.startsWith("www.") ? domain.substring(4) : domain) : null;
    }
}
