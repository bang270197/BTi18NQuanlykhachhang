package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/show-province")
    public String showProvince(Model model){
        Iterable<Province> province=provinceService.showAll();
        model.addAttribute("province",province);
        return "province/list";
    }

    @GetMapping("/show-createProvince")
    public String showCreate(Model model){
        model.addAttribute("province",new Province());
        return "province/create";
    }

    @PostMapping("/create-province")
    public String createProvince(@ModelAttribute Province province,Model model){
        provinceService.save(province);
        model.addAttribute("province",province);
        model.addAttribute("messageCreate","Them thanh cong");
        return "province/create";

    }
}
