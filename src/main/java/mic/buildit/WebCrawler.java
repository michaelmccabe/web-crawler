package mic.buildit;

import mic.buildit.exceptions.WebCrawlerException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WebCrawler {
    private HashSet<String> links;
    private String domain;
    private boolean saveFile;

    public WebCrawler(String URL, boolean saveFile) throws WebCrawlerException {

        links = new HashSet();
        this.domain = WebCrawl.getDomainName(URL);
        this.saveFile = saveFile;
        if(saveFile==true) {
            String str = "List of links: \n";
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(domain + ".txt"));
                writer.write(str);
            } catch (IOException e) {
                throw new WebCrawlerException(e.getMessage());
            }
        }

    }

    public HashSet<String> getPageLinks(String URL) throws WebCrawlerException {

        try {
            if(saveFile==true) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(domain + ".txt", true));
                writer.append(URL);
                writer.append("\n");
                writer.close();
            }
            System.out.println("getPageLinks for : "  + URL);

            Document document = Jsoup.connect(URL).get();
            Elements linksOnPage = document.select("a[href]");

            Set<String> set = linksOnPage
                    .stream()
                    .map(lop -> lop.attr("abs:href"))
                    .filter(l ->  l.startsWith("https://" + domain) || l.startsWith("http://" + domain))
                    .filter(r -> !links.contains(r))
                    .collect(Collectors.toSet());

            links.addAll(set);

            set.forEach(url -> {
                    getPageLinks(url);
            });

        } catch (IOException e) {
            System.err.println("For '" + URL + "': " + e.getMessage());
            throw new WebCrawlerException("URL is not reachable");
        }

        return links;
    }


}
