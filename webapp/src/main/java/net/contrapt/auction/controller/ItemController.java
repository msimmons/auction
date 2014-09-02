package net.contrapt.auction.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class ItemController extends BaseController {

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public Item get(@PathVariable(value = "itemId") Integer itemId) {
        return items[itemId-1];
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public List<Item> query() {
        return Arrays.asList(items);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public Item save(@RequestBody Item item) {
        items[item.getId()-1] = item;
        return item;
    }

    private static Item[] items = new Item[] {
          new Item(1, "Jesse's Apple Pie"),
          new Item(2, "Farm Share"),
          new Item(3, "Weekend in Vermont")
    };

    private static class Item {
        Integer id;
        String name;
        List<BidController.Bid> bids = new ArrayList<BidController.Bid>();

        Item() {}

        Item(Integer id, String name) {
            this.id = id;
            this.name =name;
        }

        public Integer getId() {return id;}
        public String getName() {return name;}
        public List<BidController.Bid> getBids() {return bids;}
    }
}
