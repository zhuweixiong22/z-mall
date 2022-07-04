package com.wyu.zmall.dto;

import com.wyu.zmall.vaildators.PasswordEqual;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;

/**
 * @author zwx
 * @date 2022-06-27 22:27
 */
@PasswordEqual(min = 2, max = 10, message = "两次密码不相等")
public class PersonDTO {
    @Length(min = 2, max = 10, message = "name 长度错误")
    private String name;

    @Range(min = 0, max = 200)
    private Integer age;

    private String password1;

    private String password2;

    // TODO 级联校验（嵌套对象）需要在关联的对象上加上@Valid注解
    @Valid
    private SchoolDTO schoolDTO;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SchoolDTO getSchoolDTO() {
        return schoolDTO;
    }

    public void setSchoolDTO(SchoolDTO schoolDTO) {
        this.schoolDTO = schoolDTO;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
