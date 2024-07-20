package org.example.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chat", schema = "public")
public class Chat {

    public Chat(long chatId){
        this.id = chatId;
    }

    @Id
    private long id;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "chat_link",
            schema = "public",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name="link_id")
    )
    private Set<Link> links = new HashSet<>();

}
