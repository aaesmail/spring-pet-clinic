package com.example.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public static final String VIEW_INDEX = "index";
    public static final String VIEW_NOT_IMPLEMENTED = "notimplemented";

    @GetMapping({ "", "/", "index", "index.html" })
    public String index() {
        return VIEW_INDEX;
    }

    @GetMapping("oups")
    public String oupsHandler() {
        return VIEW_NOT_IMPLEMENTED;
    }
}
