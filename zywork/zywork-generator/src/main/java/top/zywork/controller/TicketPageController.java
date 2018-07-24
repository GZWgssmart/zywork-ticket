package top.zywork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.zywork.wechat.WechatAPI;

import java.io.IOException;

@Controller
@RequestMapping("/ticket-page")
public class TicketPageController {

    @GetMapping("seat")
    public ModelAndView seat(ModelAndView modelAndView, String itemId, String floor, String openid) {
        modelAndView.setViewName("/front/seat");
        modelAndView.addObject("itemId", itemId);
        modelAndView.addObject("floor", floor);
        modelAndView.addObject("openid", openid);
        return modelAndView;
    }

    @GetMapping("user")
    public ModelAndView user(ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/user"));
        return modelAndView;
    }

    @GetMapping("ticket-item")
    public ModelAndView ticketItem(ModelAndView modelAndView) {
//        modelAndView.setViewName("/front/ticket_item");
//        return modelAndView;
        modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/ticket_item"));
        return modelAndView;
    }

    @GetMapping("ticket-item-detail/{itemId}/{openid}")
    public ModelAndView ticketItemDetail(ModelAndView modelAndView, @PathVariable("itemId") String itemId, @PathVariable("openid") String openid) {
        modelAndView.setViewName("/front/ticket_item_detail");
        modelAndView.addObject("itemId", itemId);
        modelAndView.addObject("openid", openid);
        return modelAndView;
    }

    @GetMapping("ticket-order/{openid}")
    public ModelAndView ticketHistory(ModelAndView modelAndView, @PathVariable("openid") String openid) {
        modelAndView.setViewName("/front/ticket_order");
        modelAndView.addObject("openid", openid);
        return modelAndView;
    }
}
