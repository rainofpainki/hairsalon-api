package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.*;
import com.rainofpainki.hairsalonapi.entity.Procedure;
import com.rainofpainki.hairsalonapi.entity.Shop;
import com.rainofpainki.hairsalonapi.entity.Stylist;
import com.rainofpainki.hairsalonapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class ShopService {

    @Autowired
    ShopRepository repository;

    @Transactional(readOnly = true)
    public Page<ShopForList> getShopList(Pageable pageable) {
        Page<Shop> entities = repository.findAll(pageable);
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
        return new PageImpl<>(shopList, pageable, entities.getTotalElements());
    }

    @Transactional(readOnly = true)
    public ShopInfo getShopInfo(Long id) {
        Optional<Shop> shopOptional = repository.findById(id);
        Shop shopEntity = shopOptional.orElseThrow(() -> new NoSuchElementException());

        // 헤어샵 정보
        ShopForShopInfo shop = ShopForShopInfo.builder()
                .shopId(shopEntity.getShopId())
                .shopName(shopEntity.getShopName())
                .shopThumbUrl(shopEntity.getShopThumbUrl())
                .shopAddress(shopEntity.getShopAddress())
                .shopBusinessHours(this.getBusinessHours(shopEntity))
                .shopTelNumber(shopEntity.getShopTelNumber())
                .shopMessage(shopEntity.getShopMessage())
                .build();

        // 시술 정보
        List<ProcedureForShopInfo> procedures = new ArrayList<>();
        List<Procedure> procedureEntities = shopEntity.getProcedures();
        for(Procedure procedureEntity : procedureEntities) {
            procedures.add(ProcedureForShopInfo.builder()
                    .procedureId(procedureEntity.getProcedureId())
                    .procedureName(procedureEntity.getProcedureName())
                    .procedurePrice(procedureEntity.getProcedurePrice())
                    .procedureHours(procedureEntity.getProcedureHours())
                    .build()
            );
        }

        // 스타일리스트 정보
        List<StylistForShopInfo> stylists = new ArrayList<>();
        List<Stylist> stylistEntities = shopEntity.getStylists();
        for(Stylist stylistEntity : stylistEntities) {
            stylists.add(StylistForShopInfo.builder()
                    .stylistId(stylistEntity.getStylistId())
                    .stylistName(stylistEntity.getStylistName())
                    .stylistMessage(stylistEntity.getStylistMessage())
                    .stylistThumbUrl(stylistEntity.getStylistThumbUrl())
                    .build()
            );
        }

        return ShopInfo.builder()
                .shop(shop)
                .procedures(procedures)
                .stylists(stylists)
                .build();

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
