package net.contrapt.auction.service.impl;

import net.contrapt.auction.model.Item;
import net.contrapt.auction.model.ItemRepository;
import net.contrapt.auction.model.ItemSummary;
import net.contrapt.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msimmons on 10/28/14.
 */
public class RepoItemService implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<ItemSummary> getItems() {
        return itemRepository.findAllSummary();
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
