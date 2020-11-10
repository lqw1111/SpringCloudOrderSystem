package com.aispringcloud.controller;

import com.aispringcloud.entity.Menu;
import com.aispringcloud.entity.MenuVO;
import com.aispringcloud.entity.Type;
import com.aispringcloud.repository.MenuRepository;
import com.aispringcloud.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Value("${server.port}")
    private String port;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/index")
    public String index() {
        return "menu port: " + this.port;
    }

    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        List<Menu> list = menuRepository.findAll(index, limit);
        return new MenuVO(0, "", menuRepository.count(), list);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes() {
        return typeRepository.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Menu menu) {
        menuRepository.save(menu);
    }

    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") int id) {
        return menuRepository.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Menu menu) {
        menuRepository.update(menu);
    }
}
