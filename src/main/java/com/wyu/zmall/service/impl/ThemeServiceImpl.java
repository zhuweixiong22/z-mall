package com.wyu.zmall.service.impl;

import com.wyu.zmall.model.Theme;
import com.wyu.zmall.repository.ThemeRepository;
import com.wyu.zmall.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-11 14:24
 */
@Service
public class ThemeServiceImpl implements ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public List<Theme> getByNames(List<String> names) {
        return this.themeRepository.findByNameIn(names);
    }

    @Override
    public Optional<Theme> getByName(String name) {
        return this.themeRepository.findByName(name);
    }
}
