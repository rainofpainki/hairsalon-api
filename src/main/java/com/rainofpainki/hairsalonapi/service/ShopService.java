package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.response.ShopListResponse;

import java.util.List;

public interface ShopService {

    public List<ShopListResponse> getShopList();
}
