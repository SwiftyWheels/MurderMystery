package com.patrickhogg.murdermystery.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Patrick Hogg
 */
@RestController
@RequestMapping("/api/images/")
public class RESTImageAccessController {

    @GetMapping("/getImage/{name}/{id}")
    @ResponseBody
    public Map<String, String> getImageFile(@PathVariable String name,
                                            @PathVariable int id)
            throws IOException {
        String path = "/static/imgs/characters/" + name.toLowerCase() + "/" + id
                      + ".jpg";

        URI url = new ClassPathResource(path).getURI();
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("url", url.getPath());
        System.out.println(urlMap);

        return urlMap;
    }

}
