package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.ShopForList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShopService {

    public Page<ShopForList> getShopList(Pageable pageable);
}
