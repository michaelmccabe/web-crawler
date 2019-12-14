package mic.buildit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlTest {

    @Test
    void checkURLisWellFormed(){
        String[] args = {"http://wiprodigital.com", "false"};
        WebCrawl.main(args);
    }
}