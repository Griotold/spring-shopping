package com.example.repository;

import com.example.constant.ItemSellStatus;
import com.example.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    // 아이템 리스트 열개
    public void createItemList() {
        for (int i = 1; i <= 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() throws Exception {
        // given
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());

        // when
        Item savedItem = itemRepository.save(item);

        // then
        System.out.println("savedItem = " + savedItem);
    }

    @Test
    @DisplayName("상품명으로 조회하기")
    public void findByItemNmTest() throws Exception {
        // given
        createItemList();

        // when
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for (Item item : itemList) {
            System.out.println("item = " + item);
        }
        // then
    }

    @Test
    @DisplayName("상품명 또는 상품 상세 설명으로 조회하기")
    public void findByItemNmOrItemDetailTest() throws Exception {
        // given
        createItemList();

        // when
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        for (Item item : itemList) {
            System.out.println("item = " + item);
        }
        // then
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByLessThanTest() throws Exception {
        // given
        createItemList();

        // when
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println("item = " + item);
        }

        // then
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void 가격_내림차순_테스트()throws Exception {
        // given
        createItemList();

        // when
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList) {
            System.out.println("item = " + item);
        }

        // then
    }
}