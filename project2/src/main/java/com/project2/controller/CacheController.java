package com.project2.controller;

import com.project2.dto.ReponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("")
    public ReponseDTO<List<String>> getCaches(){
        return ReponseDTO.<List<String>>builder()
                .data(cacheManager.getCacheNames().stream().collect(Collectors.toList()))
                .status(200)
                .msg("Get caches success")
                .build();
    }
    @DeleteMapping("/{name}")
    public ReponseDTO<Void> deleteCaches(@PathVariable("name") String name){
        Cache cache = cacheManager.getCache(name);
        if(cache != null){
            cache.clear();
        }
        return ReponseDTO.<Void>builder()
                .status(200)
                .msg("Clear caches success")
                .build();
    }

}
