package net.contrapt.auction.service;

import net.contrapt.auction.model.Item;

import java.util.List;

/**
 * Created by msimmons on 10/28/14.
 */
public interface ItemService {

    public List<Item> getItems();

    public Item saveItem(Item item);

    public Item getItem(Long itemId);
}
