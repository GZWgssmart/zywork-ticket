package top.zywork.generator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 显示首页和主页的控制器<br/>
 *
 * 创建于2018-03-22<br/>
 *
 * @author 王振宇
 * @version 1.0
 */
@Controller
@RequestMapping("/generator")
public class GeneratorIndexController {

    @GetMapping("index")
    public String index() {
        return "generator/index";
    }

    @GetMapping("home")
    public String home() {
        return "generator/home";
    }

}
