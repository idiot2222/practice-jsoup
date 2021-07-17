package me.bogeun.jsoup.controller;

import lombok.RequiredArgsConstructor;
import me.bogeun.jsoup.dto.ArticleDto;
import me.bogeun.jsoup.jsoup.ArticleCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final ArticleCrawler articleCrawler;

    @GetMapping("/test")
    public String getTest(String keyword, Model model) {
        // 4. Bean으로 등록해둔 ArticleCrawler로부터 해당 키워드의
        //   기사 제목과 url들을 받아옴.
        List<ArticleDto> articleList = articleCrawler.getArticleLists(keyword);

        model.addAttribute("articleList", articleList);

        return "test";
    }
}
