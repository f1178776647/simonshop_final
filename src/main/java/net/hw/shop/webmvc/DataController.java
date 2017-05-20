package net.hw.shop.webmvc;

import net.hw.shop.util.SQLFileExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by howard on 2017/5/11.
 */
@Controller
@RequestMapping("/data")
public class DataController {
    @RequestMapping("/restoreData")
    public String restoreData(Model model) {
        if (SQLFileExecutor.execute("simonshop_final.sql")) {
            model.addAttribute("dataMsg","恭喜，数据还原成功！");
        } else {
            model.addAttribute("dataMsg", "遗憾，数据还原失败！");
        }
        return "backend/restoreData";
    }
}
