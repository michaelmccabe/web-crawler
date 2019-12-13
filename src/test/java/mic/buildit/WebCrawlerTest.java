package mic.buildit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerTest {


    @Test
    void checkURL() {

        WebCrawler underTest = new WebCrawler("http://wiprodigital.com");
        underTest.getPageLinks("http://wiprodigital.com");

    }

}