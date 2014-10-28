package net.contrapt.auction.controller;

import net.contrapt.auction.model.Item;
import net.contrapt.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class ItemController extends BaseController {

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public Item get(@PathVariable(value = "itemId") Long itemId) {
        return itemService.getItem(itemId);
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public List<Item> query() {
        return itemService.getItems();
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public Item save(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

}
