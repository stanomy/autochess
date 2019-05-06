package com.sy.autochess.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Description TODO
 * @Author sy
 * @Version 1.0
 * @Time 2019/5/6 15:42
 **/
public class GithubRepoPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());
        GithubRepo githubRepo = new GithubRepo();
        githubRepo.setAuthor(page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
        githubRepo.setName(page.getHtml().xpath("//h1[contains(@class, 'entry-title') and contains(@class, 'public')]/strong/a/text()").toString());
        githubRepo.setReadme(page.getHtml().xpath("//div[@id='readme']/tidyText()").toString());
        if (githubRepo.getName() == null) {
            //skip this page
            page.setSkip(true);
        } else {
            page.putField("repo", githubRepo);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
    }
}
