package com.wyu.zmall.service;

import com.wyu.zmall.model.Theme;

import java.util.List;
import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-11 14:24
 */
public interface ThemeService {

    List<Theme> getByNames(List<String> names);

    Optional<Theme> getByName(String name);
}
