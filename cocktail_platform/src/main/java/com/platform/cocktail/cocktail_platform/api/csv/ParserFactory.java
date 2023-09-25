package com.platform.cocktail.cocktail_platform.api.csv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.platform.cocktail.cocktail_platform.domain.Menu;

@Configuration
public class ParserFactory {
    @Bean
    public ReadLineContext<Menu> hospitalReadLineContextest(){
        return new ReadLineContext<Menu>(new MenuParser());
    }
}
