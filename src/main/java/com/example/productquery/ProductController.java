package com.example.productquery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {

    // 模擬網路費率數據
    private List<Object[]> rates = Arrays.asList(
            new Object[]{"2G/1G 進階型", "$1,789"},
            new Object[]{"2G/1G 商用型", "$1,446"},
            new Object[]{"2G/1G 家用型", "$1,111"},
            new Object[]{"1G/1G 商用型", "$1,025"}
    );

    @GetMapping("/")
    public ModelAndView getRates() {
        ModelAndView modelAndView = new ModelAndView("productTable");
        modelAndView.addObject("rates", rates);
        return modelAndView;
    }
}