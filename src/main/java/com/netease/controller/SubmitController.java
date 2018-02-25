package com.netease.controller;

import com.netease.pojo.Item;
import com.netease.service.ItemService;
import com.netease.util.Format;
import com.netease.util.Identity;
import com.netease.util.Response;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping(value = "/system/api")
public class SubmitController {

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
                             HttpSession session, ModelMap modelMap)
            throws UnsupportedEncodingException {
        if (!Identity.isSeller(session)) {
            return "error";
        }
        Item item = new Item();
        Map<String, String> decode = Format.decodeString2Map(itemForm);
        for (String key:decode.keySet()){
            System.out.println(key+"---------------"+decode.get(key));
        }
        item.setTitle(decode.get("title"));
        item.setDescription(decode.get("summary"));
        item.setDetail(decode.get("detail"));
        item.setPrice(100 * Integer.valueOf(decode.get("price")));
        item.setImage(decode.get("image"));
        itemService.updateItem(id, item);
        modelMap.addAttribute("id", id);
        return "editSubmit";
    }

    @RequestMapping("/publish")
    public String publish(HttpSession session) {
        if (!Identity.isSeller(session)) {
            return "error";
        }
        return "publish";
    }

    @RequestMapping(value = "/publishSubmit")
    public String publishSubmit(@RequestBody String itemForm, HttpSession session, ModelMap modelMap)
            throws UnsupportedEncodingException {
        if (!Identity.isSeller(session)) {
            return "error";
        }
        Map<String, String> decode = Format.decodeString2Map(itemForm);

        Item item = new Item();
        item.setTitle(decode.get("title"));
        item.setDescription(decode.get("summary"));
        item.setDetail(decode.get("detail"));
        item.setPrice(100 * Integer.valueOf(decode.get("price")));
        item.setImage(decode.get("image"));
        int id = itemService.addItem(item);
        modelMap.addAttribute("id", id);
        return "publishSubmit";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(@RequestParam("id") int id){
        boolean result = itemService.deleteItem(id);
        if (result) return Response.build(200, "删除成功", true);
        else return Response.build(300, "删除失败", false);
    }
}
