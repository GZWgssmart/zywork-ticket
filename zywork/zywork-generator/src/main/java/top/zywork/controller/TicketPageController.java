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

    @GetMapping("user")
    public ModelAndView user(ModelAndView modelAndView) throws IOException {
        modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/user"));
        return modelAndView;
    }

    @GetMapping("ticket-item")
    public ModelAndView ticketItem(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/ticket_item"));
        return modelAndView;
    }

    @GetMapping("ticket-item-admin")
    public ModelAndView ticketItemAdmin(ModelAndView modelAndView) {
        modelAndView.setViewName("/front/ticket_item_admin");
        return modelAndView;
    }

    @GetMapping("ticket-item-detail")
    public ModelAndView ticketItemDetail(ModelAndView modelAndView, String itemId, String openid) {
        modelAndView.setViewName("/front/ticket_item_detail");
        modelAndView.addObject("itemId", itemId);
        modelAndView.addObject("openid", openid);
        return modelAndView;
    }

    @GetMapping("ticket-item-detail-admin")
    public ModelAndView ticketItemDetailAdmin(ModelAndView modelAndView, String itemId) {
        modelAndView.setViewName("/front/ticket_item_detail_admin");
        modelAndView.addObject("itemId", itemId);
        return modelAndView;
    }

    @GetMapping("ticket-item-detail-time")
    public ModelAndView ticketItemDetailTime(ModelAndView modelAndView, String itemId, String openid) {
        modelAndView.setViewName("/front/ticket_item_detail_time");
        modelAndView.addObject("itemId", itemId);
        modelAndView.addObject("openid", openid);
        return modelAndView;
    }

    @GetMapping("ticket-item-detail-time-admin")
    public ModelAndView ticketItemDetailTimeAdmin(ModelAndView modelAndView, String itemId) {
        modelAndView.setViewName("/front/ticket_item_detail_time_admin");
        modelAndView.addObject("itemId", itemId);
        return modelAndView;
    }

    @GetMapping("seat")
    public ModelAndView seat(ModelAndView modelAndView, String itemId,  String openid, String time, String address) {
        modelAndView.setViewName("/front/seat");
        modelAndView.addObject("itemId", itemId);
        modelAndView.addObject("openid", openid);
        modelAndView.addObject("time", time);
        modelAndView.addObject("address", address);
        return modelAndView;
    }

    @GetMapping("seat-admin")
    public ModelAndView seatAdmin(ModelAndView modelAndView, String itemId, String time, String address) {
        modelAndView.setViewName("/front/seat_admin");
        modelAndView.addObject("itemId", itemId);
        modelAndView.addObject("time", time);
        modelAndView.addObject("address", address);
        return modelAndView;
    }

    @GetMapping("ticket-order")
    public ModelAndView ticketHistory(ModelAndView modelAndView, String openid) {
        modelAndView.setViewName("/front/ticket_order");
        modelAndView.addObject("openid", openid);
        return modelAndView;
    }

    @GetMapping("map")
    public String map() {
        return "/front/map";
    }
}
