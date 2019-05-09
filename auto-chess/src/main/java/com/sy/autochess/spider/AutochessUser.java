package com.sy.autochess.spider;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * @Description TODO
 * @Author sy
 * @Version 1.0
 * @Time 2019/5/8 13:34
 **/
@TargetUrl("https://autochess\\.op\\.gg/user/[^-]+-\\d+")
public class AutochessUser {
    @ExtractBy("//h1[@style='margin-top: 10px;']/text()")
    private String userName;

    public static void main(String[] args) {
//        Spider.create(new AutochessProcessor()).addUrl("https://autochess.op.gg/user/525387699-545948").pipeline(new ConsolePipeline()).run();
        OOSpider.create(Site.me(), new ConsolePageModelPipeline(), AutochessUser.class).addUrl("https://autochess.op.gg/user/525387699-545948").run();

    }
}
