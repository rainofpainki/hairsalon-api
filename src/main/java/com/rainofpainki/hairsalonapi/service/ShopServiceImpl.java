package com.rainofpainki.hairsalonapi.service;

import com.rainofpainki.hairsalonapi.dto.projection.ShopListProjection;
import com.rainofpainki.hairsalonapi.dto.response.ShopListResponse;
import com.rainofpainki.hairsalonapi.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;

    public void getShopList() {
    }
}
