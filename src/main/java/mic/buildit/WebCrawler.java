package mic.buildit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WebCrawler {
    private HashSet<String> links;
    private String URL;
    private String domain;


    public WebCrawler(String URL) {
        links = new HashSet();
        this.URL = URL;
        this.domain = WebCrawl.getDomainName(URL);
    }

    public void getPageLinks(String URL) {

        try {
            System.out.println("getPageLinks for : "  + URL);

            Document document = Jsoup.connect(URL).get();
            Elements linksOnPage = document.select("a[href]");

            Set<String> set = linksOnPage
                    .stream()
                    .map(r -> r.attr("abs:href"))
                    .filter(r ->  r.startsWith("https://" + domain) || r.startsWith("http://" + domain))
                    .filter(r -> !links.contains(r))
                    .collect(Collectors.toSet());

            links.addAll(set);

            set.forEach(r -> {
                    getPageLinks(r);
            });

        } catch (IOException e) {
            System.err.println("For '" + URL + "': " + e.getMessage());
        }
    }

}
