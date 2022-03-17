package com.maxjonata.domain;

import java.util.SortedSet;

public interface Scrapper {
    SortedSet<Product> search(String siteToSearch);
    String scrapeInnerText(String XPath, String url);
}
