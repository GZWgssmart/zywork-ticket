package top.zywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.zywork.wechat.WechatAPI;

import java.io.IOException;

@Controller
@RequestMapping("/ticket-page")
public class TicketPageController {

    @GetMapping("seat")
    public ModelAndView seat(ModelAndView modelAndView) {
        modelAndView.setViewName("/front/seat");
        return modelAndView;
    }

    @GetMapping("user")
    public ModelAndView user(ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/user"));
        return modelAndView;
    }

    @GetMapping("ticket-item")
    public ModelAndView ticketItem(ModelAndView modelAndView) {
        modelAndView.setViewName("/front/ticket_item");
        return modelAndView;
    }

    @GetMapping("ticket-item-detail")
    public ModelAndView ticketItemDetail(ModelAndView modelAndView) {
        modelAndView.setViewName("/front/ticket_item_detail");
        return modelAndView;
    }

    @GetMapping("ticket-history")
    public ModelAndView ticketHistory(ModelAndView modelAndView) {
        modelAndView.setViewName("/front/ticket_history");
        return modelAndView;
    }
}
