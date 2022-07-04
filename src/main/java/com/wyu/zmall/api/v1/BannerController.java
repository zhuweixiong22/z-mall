package com.wyu.zmall.api.v1;

import com.wyu.zmall.dto.From;
import com.wyu.zmall.dto.PersonDTO;
import com.wyu.zmall.exception.http.ForbiddenException;
import com.wyu.zmall.exception.http.NotFoundException;
import com.wyu.zmall.model.Banner;
import com.wyu.zmall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @author zwx
 * @date 2022-06-26 22:59
 */
@RestController
@RequestMapping("/banner")
//@Validated // 写在类上只是开启非java bean的校验 若要对请求体进行校验还要在方法参数上加上
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/test/{id1}")
    public String test(@PathVariable(name = "id1") @Max(value = 10, message = "name不能超过10") Integer id, @RequestParam @Max(value = 11, message = "id2不能超过11") Integer id2) {
        System.out.println(id + " " + id2);
        //throw new Exception("error");
        //throw new ForbiddenException(10001);
        return "nihao333777";
    }

    @GetMapping("/test2")
    public String test2(@RequestBody @Validated PersonDTO personDTO) {
        //throw new Exception("error");
        System.out.println(personDTO);
        throw new ForbiddenException(10001);
        //return "nihao333777";
    }


    @PostMapping("/test3")
    public String test3(@Validated From from) {
        //throw new Exception("error");
        System.out.println(from);
        throw new ForbiddenException(10001);
        //return "nihao333777";
    }

    @GetMapping("/name/{name}")
    public Banner getByName(@PathVariable @NotBlank String name) {
        // jpa很方便的一点是 可以直接将Banner的关联对象BannerItem可以查出来（添加导航属性）
        return bannerService.getByName(name);
    }
}
