package com.patrickhogg.murdermystery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Patrick Hogg
 */
@Controller
public class MurderMysteryController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
}
