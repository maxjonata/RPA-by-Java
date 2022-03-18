package com.maxjonata.controllers.web;

import com.maxjonata.adapters.MakeAdapter;
import com.maxjonata.adapters.SeleniumChromeDriver;
import com.maxjonata.controllers.LoggerSout;
import com.maxjonata.domain.DriverManagerAdapter;
import com.maxjonata.domain.Logger;
import com.maxjonata.domain.Product;
import com.maxjonata.domain.Scrapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class GreetingController {

    @GetMapping("/searchLowestPrices")
    public Collection<Product> greeting(@RequestParam(name="website", required=true) String website,
                                        @RequestParam(name="product", required=true) String productName ,
                                        Model model) {
        Logger logger = new MakeAdapter().logger(new LoggerSout());
        DriverManagerAdapter driverManager = new SeleniumChromeDriver();
        Scrapper scrapper = new com.maxjonata.controllers.Scrapper(website, logger, driverManager);
        Collection <Product> result = scrapper.search(productName);

        model.addAttribute("products", result);
        return result;
    }

}