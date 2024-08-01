package org.example.ncone.service.impl;

import org.example.ncone.data.domain.News;
import org.example.ncone.service.NewsService;
import org.example.ncone.service.ScheduledService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    private NewsService newsService;

    @Scheduled(fixedRate = 10000)
    @Override
    public void newsScan() {
        String url = "https://news.ycombinator.com/";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https://google.com")
                    .get();

            Elements newsItems = doc.select("tr.athing");
            for (Element newsItem : newsItems) {
                // Extract the title from the titleline class
                String title = newsItem.select(".titleline a").text();
                System.out.println("Title: " + title);

                // Extract the age text for the publication time
                Element subtext = newsItem.nextElementSibling().select(".subtext").first();
                if (subtext != null) {
                    String ageText = subtext.select(".age").attr("title");
                    System.out.println("Age text: " + ageText); // Debugging output


                    // Convert ageText to LocalDateTime
                    LocalDateTime publishedTime = LocalDateTime.parse(ageText, DateTimeFormatter.ISO_DATE_TIME);

                    if (!newsService.isExist(title)) {
                        ZoneId zoneId = ZoneId.systemDefault();
                        ZonedDateTime zonedDateTime = publishedTime.atZone(zoneId);
                        News obj = new News();
                        obj.setHeadline(title);
                        obj.setPublicationTime(zonedDateTime.toInstant());
                        newsService.save(obj);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
