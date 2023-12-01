package com.quan.cloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Force-oneself
 * @date 2023-11-24
 */
@Controller
@RequestMapping("view")
public class ViewController {

    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }
}
