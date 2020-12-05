package com.gennadziy.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "justiceTest")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    public Test(String name) {
        this.name = name;
    }


    @Id
    private Object id;
    @Indexed
    private String name;


}
