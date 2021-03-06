package top.zywork.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.zywork.common.HashUtils;
import top.zywork.dto.UserDTO;
import top.zywork.enums.CharsetEnum;
import top.zywork.enums.HashEncodeEnum;
import top.zywork.service.UserService;
import top.zywork.wechat.WechatAPI;
import top.zywork.wechat.WechatUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/wechat")
public class WechatLoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public ModelAndView login(ModelAndView modelAndView, String original, String code) throws UnsupportedEncodingException {
        WechatUtil wechatUtil = new WechatUtil();
        if (code == null) {
            if (original.contains("user")) {
                modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/user"));
            } else if (original.contains("ticket_item")) {
                modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/ticket_item"));
            }
        } else {
            String accessor = wechatUtil.authLogin(code);
            if (accessor != null) {
                JSONObject accessorJSON = JSON.parseObject(accessor);
                String accessToken = accessorJSON.getString("access_token");
                if (accessToken != null) {
                    String openid = accessorJSON.getString("openid");
                    String userInfo = wechatUtil.getUserInfo(accessToken, openid);
                    userInfo = new String(userInfo.getBytes(CharsetEnum.ISO8859_1.getValue()), CharsetEnum.UTF8.getValue());
                    JSONObject userInfoJSON = JSON.parseObject(userInfo);
                    UserDTO user = new UserDTO();
                    Object obj = userService.getByOpenid(openid);
                    if (obj != null) {
                        user = (UserDTO) obj;
                    } else {
                        user.setPassword(HashUtils.md5("123456", HashEncodeEnum.HEX));
                        user.setOpenid(openid);
                        user.setNickname(EmojiParser.parseToAliases(userInfoJSON.getString("nickname"), EmojiParser.FitzpatrickAction.REMOVE));
                        user.setHeadicon(userInfoJSON.getString("headimgurl"));
                        int sex = userInfoJSON.getInteger("sex");
                        user.setGender((byte) sex);
                        userService.save(user);
                    }
                    modelAndView.addObject("user", user);
                    modelAndView.setViewName(original);
                }
            }
        }
        if (modelAndView.getViewName() == null) {
            if (original.contains("user")) {
                modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/user"));
            } else if (original.contains("ticket_item")) {
                modelAndView.setViewName("redirect:" + WechatAPI.ACCESS_LOGIN_URL.replace("{REDIRECT_URL}", WechatAPI.REDIRECT_URL + "?original=/front/ticket_item"));
            }
        }
        return modelAndView;
    }

}
