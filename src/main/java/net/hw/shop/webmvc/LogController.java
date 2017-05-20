package net.hw.shop.webmvc;

import net.hw.shop.bean.Log;
import net.hw.shop.bean.Order;
import net.hw.shop.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by howard on 2017/5/5.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @RequestMapping("/listAll")
    public
    @ResponseBody
    List<Log> listAll() {
        List<Log> logs = logService.findList(null);
        return logs;
    }
}
