package org.ubp.ent.backend.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Anthony on 21/11/2015.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}
