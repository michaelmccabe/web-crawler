# web-crawler

This application crawls a specified website and saves the unique links, for that website's domain to a file. To run you need the following installed on your machine.

- Java version 11
- Maven

### How to run as a script or via maven
You may run this service locally from a script or via maven:
*  `./wc.sh-v <URL>` compiles and runs against the specified URL (you may need to run `chmod u+x wc.sh` first)
* `mvn test` will run all the unit tests but will not, by default, save results to a file

### How it works
The `WebCrawler` class uses JSoup library to fetch the links from the given page, filtering out any which are not in the given domain and any which have already been saved. It then fetches all page links in these URLs in turn.

### Future changes
* Add option to save to a different format (e.g. xml)
* Add option to filter out parameters after ? or # in url - right now URLS with parameters are treated as different. 