package org.example.model;


import jakarta.persistence.*;
import lombok.*;
import org.example.exception.LinkNotFoundException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "chat", schema = "public")
public class Chat {

    public Chat(long chatId){
        this.id = chatId;
    }

    @Id
    private long id;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "chat_link",
            schema = "public",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name="link_id")
    )
    private Set<Link> links = new HashSet<>();


    public void addLink(Link link) {
        if (!links.contains(link)) {
            links.add(link);
            link.getChats().add(this);
        }
    }

    public void removeLink(Link link) {
        if (links.contains(link)) {
            links.remove(link);
            link.getChats().remove(this);
        }else{
            throw new LinkNotFoundException("Ссылка отсутствует в листе отслеживания для данного чата");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id && Objects.equals(links, chat.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                '}';
    }
}
