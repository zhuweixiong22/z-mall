package com.wyu.zmall.repository;

import com.wyu.zmall.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author zwx
 * @date 2022-07-11 14:28
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    List<Theme> findByNameIn(List<String> names);

    Optional<Theme> findByName(String name);
}
