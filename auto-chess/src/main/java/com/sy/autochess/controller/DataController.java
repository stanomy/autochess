package com.sy.autochess.controller;

import com.sy.autochess.util.HttpClientUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author sy
 * @Version 1.0
 * @Time 2019/5/6 11:16
 **/
@RestController
@RequestMapping("/data")
public class DataController {

    String url = "https://autochess.op.gg/user/";

    @GetMapping("/s")
    public String search(@RequestParam("name") String name) {
        String rs = HttpClientUtil.httpGetToString(url + name);
        System.out.println(rs);
        return rs;
    }
}
