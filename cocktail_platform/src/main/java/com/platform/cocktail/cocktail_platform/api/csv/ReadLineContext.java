package com.platform.cocktail.cocktail_platform.api.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadLineContext<T> {
    Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    public ArrayList<T> readByLine(String filename) throws IOException {
        // 삽입
    	ArrayList<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;
        while ((str = reader.readLine()) != null) {
            try {
                result.add(parser.parse(str));
                log.debug(str);
            }catch(Exception e){
                System.out.printf("파싱 중 문제가 생겨 이 라인은 넘어갑니다. 파일내용 : %s\n", str);
            }
        }
        reader.close();
        return result;
    }

}