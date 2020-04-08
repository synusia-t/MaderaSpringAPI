package com;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Home Page";
    }
}
