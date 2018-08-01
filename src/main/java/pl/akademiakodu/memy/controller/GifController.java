package pl.akademiakodu.memy.controller;


import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiakodu.memy.dao.GifDao;
import pl.akademiakodu.memy.dao.GifStaticDao;
import pl.akademiakodu.memy.model.Gif;
import pl.akademiakodu.memy.model.Search;

import javax.swing.*;

@Controller
public class GifController {


    private GifDao gifDao = new GifStaticDao();

    @GetMapping("/")
    public String home(ModelMap modelMap, Gif gif) {
        modelMap.put("gifs", gifDao.findAll());
        return "home";
    }


    @GetMapping("/api/posts")
    public Iterable<Gif> all() {
        return gifDao.findAll();
    }


    @GetMapping("/search")
    public String searchAll(@ModelAttribute Search search, ModelMap modelMap) {
        modelMap.put("gifs", search.search(gifDao.findAll(), search.getQ()));
        if (search.search(gifDao.findAll(), search.getQ()).size() == 0) {
            modelMap.put("alert", "Nie ma takiego gifa");
        }
        return "home";
    }

    @GetMapping("/favorites")
    public String findFavorites(ModelMap modelMap) {
        modelMap.put("favs", gifDao.findAllFavorites());
        return "favorites";
    }


}

