package servlet3.demo.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import servlet3.demo.controller.DemoController;

/**
 * @author kingwudy
 * @date 2018/4/10
 */
@Service
public class DemoService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DemoService.class);

    public Object echo(Object p0, Object p1) {
        LOGGER.info("{},{}", p0, p1);
        return Lists.newArrayList(p0, p1);
    }

}
