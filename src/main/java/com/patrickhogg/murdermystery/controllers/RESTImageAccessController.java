package com.patrickhogg.murdermystery.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/images/")
public class RESTImageAccessController {

    @GetMapping("/getImage/{name}/{id}")
    public URI getImageFile(@PathVariable String name, @PathVariable int id)
            throws IOException {

        String path = "/imgs/characters/" + name + "/" + id + ".jpg";
        return new ClassPathResource(path).getURI();
    }

}
