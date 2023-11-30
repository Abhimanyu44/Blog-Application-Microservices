package com.microservice.post.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name= "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;

    private  String title;
    private  String description;
    private  String content;
}
