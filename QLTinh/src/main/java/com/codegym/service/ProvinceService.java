package com.codegym.service;

import com.codegym.model.Province;

public interface ProvinceService {
    Iterable<Province> showAll();

    Province findById(Long id);

    void save(Province province);

    void remove(Long id);


}
