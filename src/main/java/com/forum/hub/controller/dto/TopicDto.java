package com.forum.hub.controller.dto;

import com.forum.hub.entity.Topic;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record TopicDto(
    String titulo,
    String mensagem,
    LocalDate createdAt,
    Boolean topicState,
    String autor,
    String curso
) {
    public static TopicDto fromEntity(Topic topic) {

      return new TopicDto(
          topic.getTitulo(),
          topic.getMensagem(),
          topic.getCreatedAt(),
          topic.getTopicState(),
          topic.getAutorTopic().getNome(),
          topic.getCourseTopic().getNome()
      );
    }
}
