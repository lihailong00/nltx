package com.lee.nltx.controller;

import com.lee.nltx.param.Result;
import com.lee.nltx.service.PostingService;
import com.lee.nltx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posting")
public class PostingController {
    @Autowired
    private PostingService postingService;
    @Autowired
    private UserService userService;
    /**
     * 接收值
     * header {
     *     "authorization": "token"
     * }
     * body {
     *     "title": "帖子标题",
     *     "content": "帖子内容"
     * }
     *
     * 返回值
     * 成功
     *
     * 失败
     */
    @PostMapping("/createposting")
    public Result insertPosting(@RequestBody Map<String, Object> posting,
                                @RequestHeader("authorization") String token) {
        Long userId = userService.getUserIdByToken(token);
        posting.put("userId", userId);
        posting.put("createTime", new Date());
        return postingService.insertPosting(posting);
    }

    /**
     * 接收值:
     * header {
     *     "authorization": "your token"
     * }
     * body {
     *     currentPage: int类型
     *     pageSize: int类型
     *     keyword: "搜索关键字"
     * }
     *
     * 返回值：
     * 成功
     * body {
     *     "title": "xxx",
     *     "content": "xxx",
     *     "username": "xxx",
     *     create_time: 日期格式,
     * }
     */
    @PostMapping("/getpostingbypage")
    public Result listPostingByPage(@RequestBody Map<String, Object> page) {
        return postingService.listPostingByPage(page);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token"
     * }
     * @param token
     * @return
     */
    @PostMapping("/getmyposting")
    public Result listPostingByTokenAndUserId(@RequestHeader(value = "authorization") String token) {
        Map<String, Object> posting = new HashMap<>();
        posting.put("token", token);
        return postingService.listPostingByTokenAndUserId(posting);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token"
     * }
     * body: {
     *     postingId: Long 类型
     * }
     */
    @PostMapping("/deletepostingbypostingid")
    public Result deletePostingByPostingId(@RequestBody Map<String, Object> info) {
        Long postingId = Long.parseLong(info.get("postingId").toString());
        return postingService.deletePostingByPostingId(postingId);
    }
}
