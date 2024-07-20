package org.example.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "link", schema = "public")
public class Link {

    public Link(Long tgChatId, String url){
        this.id = tgChatId;
        this.url = url;
    }

    @Id
    private long id;

    @Column(name = "l_url", nullable = false)
    private String url;

    @ManyToMany(mappedBy = "links")
    private Set<Chat> chats = new HashSet<>();

    public Link(String url) {
    }
}
