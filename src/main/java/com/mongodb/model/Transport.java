package com.mongodb.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Transport {
    private String name;
    private String address;
    private String dept;
    private String type;
}