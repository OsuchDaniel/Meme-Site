package memeSite.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import memeSite.dao.CategoryDao;
import memeSite.dao.CategoryDaoImpl;
import memeSite.dao.GifDaoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GifController {
   // @Autowired
    GifDaoImpl gifDao = new GifDaoImpl();
    CategoryDaoImpl categoryDao = new CategoryDaoImpl();


    @GetMapping("/")
    public String home( ModelMap modelMap){
        modelMap.put("gifs", gifDao.findAll());
        return "home";
    }

    @GetMapping("/favorites")
    public String favorites(ModelMap modelMap){
        modelMap.put("fav",gifDao.findFavorites());

        return "favorites";
    }

    @GetMapping("/gif/{name}")
    public String details(@PathVariable String name, ModelMap modelMap){
        modelMap.addAttribute("gif",gifDao.findOne(name));
        return "gif-details";
    }

    @GetMapping("/categories")
    public String categories(ModelMap modelMap){
        modelMap.put("categories", categoryDao.category());
        return "categories";
    }

    @GetMapping("/category/{id}")
    public String categoryDetails(@PathVariable int id, ModelMap modelMap){
        // gifs
        modelMap.put("gifs",gifDao.findGifById());
        modelMap.put("category",gifDao.findByCategoryId(id));
        // category
        return "category";
    }

}
