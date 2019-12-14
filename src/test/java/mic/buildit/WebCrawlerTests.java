package mic.buildit;

import mic.buildit.exceptions.WebCrawlerException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WebCrawlerTests {

    @Test
    void checkWorkingUrlReturnsLinks() throws Exception {
        WebCrawler underTest = new WebCrawler("http://wiprodigital.com", false);
        Set<String> links = underTest.getPageLinks("http://wiprodigital.com");
        assertTrue(links.size()>1);
    }

    @Test
    void checkNullUrlThrowsNullPointerException() {
        WebCrawlerException exception = assertThrows(WebCrawlerException.class, () -> {
            WebCrawler underTest = new WebCrawler(null, false);
        });
        assertTrue(exception.getMessage().equals("URL is not valid"));
    }

    @Test
    void checkUrlNotWellFormedReturnsLinks() {
        WebCrawlerException exception = assertThrows(WebCrawlerException.class, () -> {
            WebCrawler underTest = new WebCrawler("wiprodigital.com", false);
        });
        assertTrue(exception.getMessage().equals("URL is not valid"));
    }

    @Test
    void checkNotWorkingUrlReturnsURLNotReachble() throws Exception {
        WebCrawlerException exception = assertThrows(WebCrawlerException.class, () -> {
            WebCrawler underTest = new WebCrawler("http://wiprodigitalnotarealwebsite.com", false);
            Set<String> links = underTest.getPageLinks("http://wiprodigitalnotarealwebsite.com");        });
        assertTrue(exception.getMessage().equals("URL is not reachable"));
    }

    @Test
    void checkWorkingUrlReturnsNoLinks() throws Exception {
        WebCrawler underTest = new WebCrawler("http://example.com", false);
        Set<String> links = underTest.getPageLinks("http://example.com");
        assertTrue(links.size()==0);
    }

}