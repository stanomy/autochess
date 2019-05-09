package com.sy.autochess.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @Description TODO
 * @Author sy
 * @Version 1.0
 * @Time 2019/5/8 13:34
 **/
public class AutochessUser implements PageProcessor {


    private Site site = Site.me().setDomain("autochess.op.gg");

    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().regex("https://autochess.op.gg/api/match/detail/\\d+?.+").all();
        page.addTargetRequests(requests);
//        page.putField("title", page.getHtml().xpath("//h1[@style='margin-top: 10px;']/text()"));
//        if (page.getResultItems().get("title") == null) {
//            page.setSkip(true);
//        }
        /**
         *  -entry--win match-entry--top match-entry--
         */
        List<Selectable> list = page.getHtml().xpath("//table//tr").nodes();

        for (Selectable s : list) {

//            String id=s.xpath("/div/@id").get().replaceAll("entry-","");
            String rank = s.xpath("//div[@class='\\\"rank\\\"']//span[@class='\\\"font-weight-bold\\\"']/text()").get();
            String time = s.xpath("//div[@class='\\\"date text-muted\\\"']/@data-date").get();
            System.out.println("当局等级=" + rank + ",游戏时间=" + time);
            List<Selectable> heros = s.xpath("//div[contains(@class,'match-hero')]").nodes();
            for (Selectable a : heros) {
                String heroName = a.xpath("//img/@title").get();
                int heroRank = a.xpath("//i[contains(@class,'fa fa-star')]").nodes().size();

                System.out.println("英雄名=" + heroName + ",等级=" + heroRank);
            }
//            System.out.println(s.toString());
        }
//        page.putField("content",page.getHtml().xpath("//div[@id='neirong_box']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return this.site;
    }

    public static void main(String[] args) {
        Spider.create(new AutochessUser()).addUrl("https://autochess.op.gg/api/match/detail/1598585?date=2019-05-08%2011:08:29").pipeline(new ConsolePipeline()).run();

    }
}
