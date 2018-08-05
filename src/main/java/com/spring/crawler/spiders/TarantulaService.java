package com.spring.crawler.spiders;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Description(value = "Service that sends spiders to crawl data from external website.")
@Service
public class TarantulaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarantulaService.class);

    @Value("${target.tarantula.website}")
    private String website;


    /**
     * Method for fetching basic data about tarantulas
     *
     * @throws IOException input | output exception
     */
    public void fetchBasicData() throws IOException
    {
        // connect to target website and get document source
        Document pageSource = establishConnection(website);

        // get basic tarantula elements...
        Elements basicDataChildren = pageSource.select("div.field__item ul li");
        basicDataChildren.forEach(child -> LOGGER.info("Basic data value: {}", child.text()));

        // locating and logging lead text
        Elements leadTexts = pageSource.getElementsByClass("lead-in-text");
        leadTexts.forEach(leadText -> LOGGER.info("Lead text data value: {}", leadText.text()));

        fetchFunFacts(pageSource);
    }

    /**
     * Method for fetching fun facts about tarantula spider
     *
     * @param pageSource - target page source document
     */
    @SuppressWarnings("squid:S2629")
    private void fetchFunFacts(Document pageSource)
    {
        Element factTitle = pageSource.select("h3:containsOwn(FUN FACTS) ~ p").first();
        LOGGER.info("Fun fact: {}", factTitle.text());
    }

    /**
     * Method for establishing connection to target url and return page source
     *
     * @param targetUrl  provided website url
     * @return Document Node
     * @throws IOException input | output exception
     */
    private Document establishConnection(String targetUrl) throws IOException {
        return Jsoup.connect(targetUrl).get();
    }
}
