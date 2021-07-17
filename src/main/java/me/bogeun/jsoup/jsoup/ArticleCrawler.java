package me.bogeun.jsoup.jsoup;

import me.bogeun.jsoup.dto.ArticleDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleCrawler {
    public List<ArticleDto> getArticleLists(String keyword) {
        final String BASE_URL = "https://search.naver.com/search.naver?where=news&sm=tab_jum&query=";
        final int articleNum = 5;

        Document document = null;
        try {
            // 1. Jsoup을 이용해 네이버 뉴스 + keyword의 문서를 가져옴.
            document = Jsoup.connect(BASE_URL + keyword).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 가져온 문서 내에서 news_tit 클래스를 가진 a 태그 요소들을 리스트로 모음.
        Elements select = document.select("a.news_tit");

        // 3. 해당 태그의 url과 title만 따로 ArticleDto에 담아 리스트로 반환.
        return select.stream()
                .map(e -> new ArticleDto(e.attr("title"), e.attr("href")))
                .limit(articleNum)
                .collect(Collectors.toList());
    }
}