#!/bin/bash
echo "url to crawl: $1"
eval 'mvn compile exec:java -Dexec.mainClass="mic.buildit.WebCrawl" -Dexec.args="$1"'