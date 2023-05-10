package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Override
    public List<ShopForList> getShopList() {
        List<Shop> entities = shopRepository.findAll();
        List<ShopForList> shopList = new ArrayList<>();
        for(Shop shop : entities) {
            shopList.add(
                ShopForList.builder()
                        .shopId(shop.getShopId())
                        .shopName(shop.getShopName())
                        .shopThumbUrl(shop.getShopThumbUrl())
                        .shopAddress(shop.getShopAddress())
                        .shopBusinessHours(this.getBusinessHours(shop))
                        .shopTelNumber(shop.getShopTelNumber())
                        .build()
            );
        }
        return shopList;
    }

    // 영업시간을 요구사항에 맞게 HashMap으로 변환한다.
    private Map<String, String> getBusinessHours(Shop shop) {
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        Map<String, String> businessHours = new HashMap<>();
        for(DayOfWeek dayOfWeek : dayOfWeeks) {
            String key = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault());
            String value;
            if(shop.getShopHolidayOfWeek() != null && dayOfWeek.getValue() == shop.getShopHolidayOfWeek()) {
                value = "정기휴무 (매주 " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()) + ")";
            } else {
                if(dayOfWeek.getValue() >= 6) {
                    value = shop.getShopStartHourOfWeekend() + " - " + shop.getShopEndHourOfWeekend();
                } else {
                    value = shop.getShopStartHourOfWeekday() + " - " + shop.getShopEndHourOfWeekday();
                }
            }
            businessHours.put(key, value);
        }
        return businessHours;
    }
}
