package com.netease.controller;

import com.netease.service.ItemService;
import com.netease.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;


@Controller
public class HelperController {
    @Resource
    private ItemService itemService;

    @RequestMapping("/")
    public String showIndex(){
        return "redirect:/system";
    }

    @RequestMapping("sales/api/delete")
    @ResponseBody
    public Map delete(@RequestParam("id") int id){
        boolean result = itemService.deleteItem(id);
        if (result) return Response.build(200, "删除成功", true);
        else return Response.build(300, "删除失败", false);
    }
}
