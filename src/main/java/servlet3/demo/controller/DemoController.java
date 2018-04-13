package servlet3.demo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import servlet3.demo.service.DemoService;

/**
 * @author kingwudy
 * @date 2018/4/10
 */
//@Controller
@RestController
@RequestMapping("/demo")
public class DemoController {
    private final static Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Value("${author}")
    private String author;

    @Autowired
    private DemoService demoService;

//    @ResponseBody
    @RequestMapping("/echo")
    public Object echo(@RequestParam Object p0, @RequestParam("p1") Object p1) {
        String p0Str = JSON.toJSONString(p0);
        String p1Str = JSON.toJSONString(p1);
        LOGGER.info("{},{},{}", author, p0Str, p1Str);
        return demoService.echo(p0, p1);
    }
}
