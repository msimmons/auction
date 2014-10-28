package net.contrapt.auction.service.impl;

import net.contrapt.auction.model.Item;
import net.contrapt.auction.model.ItemRepository;
import net.contrapt.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msimmons on 10/28/14.
 */
public class RepoItemService implements ItemService {

    private static Item[] items = new Item[] {
          new Item("Jesse's Apple Pie"),
          new Item("Farm Share"),
          new Item("Weekend in Vermont")
    };

    @Autowired
    ItemRepository itemRepository;

    @PostConstruct
    public void addItems() {
        for ( Item item : items ) {
            itemRepository.save(item);
        }
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<Item>();
        for ( Item item : itemRepository.findAll() ) {
            items.add(item);
        }
        return items;
    }

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
