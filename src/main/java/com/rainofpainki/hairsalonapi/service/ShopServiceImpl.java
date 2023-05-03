package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.projection.ShopListProjection;
import com.rainofpainki.hairsalonapi.dto.response.ShopListResponse;
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

    /**
     * 헤어샵의 영업시간 데이터를 반환한다.
     * @param shop
     * @return
     */
    private Map<String, String> getBusinessHours(ShopListProjection shop) {
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

    /**
     * 헤어샵의 목록을 반환한다.
     * @return
     */
    public List<ShopListResponse> getShopList() {
        List<ShopListProjection> shopList = shopRepository.queryDslFindAll();
        List<ShopListResponse> shopResponseList = new ArrayList<>();
        
        for(ShopListProjection shop : shopList) {
            shopResponseList.add(
                ShopListResponse.builder()
                        .shopId(shop.getShopId())
                        .shopName(shop.getShopName())
                        .shopThumbUrl(shop.getShopThumbUrl())
                        .shopAddress(shop.getShopAddress())
                        .shopBusinessHours(this.getBusinessHours(shop))
                        .shopTelNumber(shop.getShopTelNumber())
                        .build()
            );
        }
        return shopResponseList;
    }
}
