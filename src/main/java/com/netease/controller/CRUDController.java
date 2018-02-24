package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.service.ItemService;
import com.netease.util.Identity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/system/api")
public class CRUDController {

    @Resource
    private ItemService itemService;

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") int id, HttpSession session, ModelMap map) {
        if (!Identity.isSeller(session)) {
            return "error";
        }
        Item item = itemService.getItemById(id);
        map.addAttribute("item", item);
        return "edit";
    }
}
