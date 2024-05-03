package com.example.sina.service;


import com.example.sina.repository.DataModelRepository;
import com.example.sina.entity.DataModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;


@Service
public class Services {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";

    public final static String DUCKDUCKGO_SEARCH_URL = "https://duckduckgo.com/html";

    @Autowired
    private DataModelRepository repository;

    public ArrayList<String> callGoogleAndDuckDuckGo(String topic) throws IOException {
        ArrayList<String> googleUrls = this.searchOnGoogle(topic);
        ArrayList<String> duckduckgoUrls = this.searchOnDuckduckgo(topic);
        ArrayList<String> bothUrlList = new ArrayList<>();

        for (String googleUrl : googleUrls) {
            bothUrlList.add(googleUrl);
            if (duckduckgoUrls.contains(googleUrl)) {

                System.out.println( "same :" + googleUrl);
            } else {

                System.out.println("difference :" + googleUrl);
            }
        }
        for (String duckduckgoUrl : duckduckgoUrls) {
            if (googleUrls.contains(duckduckgoUrl)) {

                System.out.println("same :" + duckduckgoUrl);

            } else {

                System.out.println( "difference :" + duckduckgoUrl);
            }
        }
        bothUrlList.addAll(duckduckgoUrls);
        bothUrlList.addAll(googleUrls);

        return bothUrlList;
    }

    public void mapDetails(String topic, String searchEngine, String resultSit, String url) {
        DataModel dataModel = new DataModel();
        dataModel.setTopic(topic);
        dataModel.setSearchEngine(searchEngine);
        dataModel.setSitName(resultSit);
        dataModel.setUrl(url);
        repository.save(dataModel);
    }

    public ArrayList<String> searchOnGoogle(String topic) throws IOException {
        String googleSearchURL = GOOGLE_SEARCH_URL + "?q=" + topic + "&num=" + 40;
        Document doc = Jsoup.connect(googleSearchURL).get();

        int count = 0;
        Elements results = doc.select("a[href]");
        ArrayList<String> urls = new ArrayList<>();

        for (Element result : results) {
            if (count >= 40) {
                break;
            }
            String url = result.attr("href");
            String resultSit = result.text();
            if (resultSit.contains(".")) {
                urls.add(url);
                count++;
                String searchEngine = "Google";
                this.mapDetails(topic, searchEngine, resultSit, url);
            }
        }
        return urls;

    }

    public ArrayList<String> searchOnDuckduckgo(String topic) throws IOException {
        String searchURL = DUCKDUCKGO_SEARCH_URL + "?q=" + topic +   "&num=" + 40;

        Document doc = Jsoup.connect(searchURL).header("contentType", "*/xml").get();
        Elements results = doc.select(".result__a");
        ArrayList<String> urls = new ArrayList<>();

        int count = 0;
        for (Element result : results) {
            if (count >= 40) {
                break;
            }
            String resultSit = result.text();
            String url = result.attr("href");
            urls.add(url);
            count++;
            String searchEngine = "Duckduckgo";

            this.mapDetails(topic, searchEngine, resultSit, url);
        }
        return urls;
    }

}





