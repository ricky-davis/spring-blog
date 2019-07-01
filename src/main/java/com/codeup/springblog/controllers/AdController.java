package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repos.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdController {

    private AdRepository adsDao;

    public AdController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }

    @GetMapping("/ads")
    @ResponseBody
    private Iterable<String> index() {
        return adsDao.getTitleWithGreatLengthNative();
    }


    @GetMapping("/ads-view")
    private String indexView(Model model) {
        model.addAttribute("ads", adsDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/create")
    private String create() {
        return "ads/create";
    }

    @PostMapping("/ads/create")
    private String insert(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam int priceInCents) {
        Ad adToInsert = new Ad(title, description, priceInCents);
        adsDao.save(adToInsert);
        return "redirect:/ads-view";
    }


}
