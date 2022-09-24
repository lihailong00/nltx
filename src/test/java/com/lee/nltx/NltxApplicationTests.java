package com.lee.nltx;

import com.lee.nltx.mapper.*;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.*;
import com.lee.nltx.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.transform.Source;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class NltxApplicationTests {
//    @Autowired
//    private PsgMapper psgMapper;
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private LoginMapper loginMapper;
//    @Autowired
//    private LoginService loginService;
//    @Test
//    void contextLoads() throws SQLException {
////        System.out.println(dataSource.getConnection());
////        psgMapper.insertPassage("ttt", "ddd", "ccc", new Date(), 1, 19);
//        Map<String, Object> user = new HashMap<>();
//        user.put("username", "Yin Jialun");
//        user.put("password", "oRfH0taAHt");
//        Map<String, Object> ansUser = loginMapper.login(user);
//        if (ansUser == null) {
//            System.out.println("登录失败！");
//        }
//        Map<String, Object> userInfo = new HashMap<>();
//        userInfo.put("username", ansUser.get("username"));
//        userInfo.put("user_id", ansUser.get("user_id"));
//        String token = JWTUtil.createToken(userInfo, 60 * 24 * 24L);
//        System.out.println(token);
//    }
//
//    @Test
//    void test02() {
////        Map<String, Object> user = new HashMap<>();
////        user.put("username", "Yin Jialun");
////        user.put("password", "oRfH0taAHt");
////        System.out.println(loginService.login(user));
//        String s = "sdfj sf";
//        System.out.println(StringUtils.isBlank(s));
//    }
//
//    @Value("${weixin.appid}")
//    private String appid;
//    @Value("${weixin.secret}")
//    private String secret;
//
//    @Test
//    void test03() {
//        System.out.println(appid + "," + secret);
//    }
//
//    @Autowired
//    private PostingMapper postingMapper;
//    @Test
//    void test04() {
//
//    }
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private PostingService postingService;
//    @Test
//    void test05() {
//
//    }
//
//    @Autowired
//    private GoodsMapper goodsMapper;
//    @Test
//    public void test06() {
//        Map<String, Object> mp = new HashMap<>();
//        mp.put("currentPage1", 1);
//        mp.put("pageSize", 10);
//        mp.put("keyword", "");
//        mp.put("goodsType", 1);
//
//        List<Map<String, Object>> goods = goodsMapper.listGoodsByPage1(mp);
//        for (Map<String, Object> good : goods) {
//            System.out.println(good);
//        }
//    }
//
//    @Autowired
//    private HomeMsgMapper homeMsgMapper;
//    @Autowired
//    private UserService userService;
//
//    @Test
//    void test07() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", 184);
//        System.out.println(postingMapper.listPostingByTokenAndUserId(map));
//    }
//
//    @Test
//    void test08() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", "mBuX2eVrVn");
//        System.out.println(postingService.listPostingByTokenAndUserId(map));
//    }
//
//    @Autowired
//    private SayLoveService sayLoveService;
//    @Test
//    void test09() {
//        Map<String, Object> mp = new HashMap<>();
//        mp.put("token", "dasfdasf");
//        System.out.println(sayLoveService.listSayLoveByTokenAndUserId(mp));
//    }
//
//    @Test
//    void test10() {
//        Map<String, Object> mp = new HashMap<>();
//        mp.put("title", "找对象！");
//        mp.put("content", "俺是大六的同学");
//        mp.put("userId", 1L);
//        mp.put("createTime", new Date());
//        sayLoveService.insertSayLove(mp);
//    }
//
//    @Autowired
//    private GoodsService goodsService;
//    @Test
//    void test11() {
//        Result goods = goodsService.listGoodsByUserId(21L);
//        System.out.println(goods);
//    }
//
//    @Test
//    void test12() {
//        goodsService.deleteGoodsByGoodsId(1017L);
//    }
}
