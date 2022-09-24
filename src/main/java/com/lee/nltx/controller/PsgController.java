package com.lee.nltx.controller;

import com.lee.nltx.param.Result;
import com.lee.nltx.service.PsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/lhl/psg")
public class PsgController {
    @Autowired
    private PsgService psgService;

    /**
     * {
     *     currentPage: xxx
     *     pageSize: xxx
     * }
     */
    @PostMapping("/bypage")
    public Result listPassageByPage(@RequestBody Map<String, Object> page) {
        int currentPage = (int) page.get("currentPage");
        int pageSize = (int) page.get("pageSize");
        return psgService.listPassageByPage(currentPage, pageSize);
    }

    /**
     * {
     *     "title": "标题",
     *     "description": "文章描述",
     *     "content": "文章内容"
     * }
     */
    @PostMapping("/insert")
    public Result insertPassage(@RequestBody Map<String, Object> passage) {
        return psgService.insertPassage(passage);
    }

    @GetMapping("/get")
    public Result getPassageById(@RequestParam(value = "passageId") Long passageId) {
        return psgService.getPassageById(passageId);
    }

    @GetMapping("/hi")
    public String func() {
        return "hi";
    }
}
