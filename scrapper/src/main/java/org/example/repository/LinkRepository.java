package org.example.repository;

import org.example.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByUrl(String url);

    List<Link> findAllByChatsId(Long chatId);
}
