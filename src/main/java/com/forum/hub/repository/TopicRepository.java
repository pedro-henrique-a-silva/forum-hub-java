package com.forum.hub.repository;

import com.forum.hub.entity.Topic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
  Optional<Topic> findByMensagem(String mensagem);

}
