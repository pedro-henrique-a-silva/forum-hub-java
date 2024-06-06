package com.forum.hub.controller.dto;

import com.forum.hub.entity.Topic;
import jakarta.validation.constraints.NotBlank;

public record TopicCreationDto(
    @NotBlank
    String titulo,

    @NotBlank
    String mensagem,

    @NotBlank
    String autor,

    @NotBlank
    String curso
) {

    public Topic toEntity() {
        Topic topic = new Topic();

        topic.setTitulo(titulo);
        topic.setMensagem(mensagem);
        topic.setNameAutor(autor);
        topic.setNameCourse(curso);

        return topic;
    }
}
