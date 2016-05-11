package net.contrapt.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by msimmons on 10/28/14.
 */
public interface ItemService {

    public List<ItemSummary> getItemSummaries();

    public Item saveItem(Item item);

    public Item getItem(Long itemId);

    public class Impl implements ItemService {

        @Autowired
        ItemRepository itemRepository;

        @Override
        @Transactional
        public List<ItemSummary> getItemSummaries() {
            return itemRepository.findAllSummary();
        }

        @Override
        @Transactional
        public Item saveItem(Item item) {
            return itemRepository.save(item);
        }

        @Override
        @Transactional
        public Item getItem(Long itemId) {
            return itemRepository.findOne(itemId);
        }

    }
}
