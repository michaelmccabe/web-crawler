package mic.buildit.exceptions;

public class WebCrawlerException extends RuntimeException {
    public WebCrawlerException(String template, String... values) {
        super(String.format(template, (Object[]) values));
    }
}