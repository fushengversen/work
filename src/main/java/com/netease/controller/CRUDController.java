package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.pojo.User;
import com.netease.service.ItemService;
import com.netease.util.Format;
import com.netease.util.Identity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

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

    @RequestMapping("/editSubmit")
    @Transactional
    public String editSubmit(@RequestParam("id") int id, @RequestBody String itemForm,
                             HttpSession session, ModelMap modelMap) throws UnsupportedEncodingException {
        if (!Identity.isSeller(session)) {
            return "error";
        }
        Item item = new Item();
        Map<String, String> decode = Format.form(URLDecoder.decode(itemForm, "utf-8"));
        item.setTitle(decode.get("title"));
        item.setDescription(decode.get("description"));
        item.setDetail(decode.get("detail"));
        item.setPrice(100 * Integer.valueOf(decode.get("price")));
        itemService.updateItem(id, item);
        modelMap.addAttribute("id", id);
        return "editSubmit";
    }
}
