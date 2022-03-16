package com.maxjonata.domain;
import java.util.*;

public interface Scrapper {
    SortedSet<Product> search(String siteToSearch);
}
