package com.forum.hub.controller;

import com.forum.hub.controller.dto.TopicCreationDto;
import com.forum.hub.controller.dto.TopicDto;
import com.forum.hub.entity.Topic;
import com.forum.hub.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {
  private TopicService topicService;

  @Autowired
  public TopicController(
      TopicService topicService
  ){
    this.topicService = topicService;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicDto> createTopic(@RequestBody @Valid TopicCreationDto topic) {
    TopicDto createdTopic = topicService.createTopic(topic.toEntity());

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTopic);
  }

  @GetMapping
  public ResponseEntity<List<TopicDto>> getAllTopics(
      @PageableDefault(
          size=10,
          page=0,
          sort={"courseTopic.nome"},
          direction= Sort.Direction.ASC) Pageable pageable
  ) {
    List<TopicDto> topics = topicService.getAllTopics(pageable);

    return ResponseEntity.ok(topics);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TopicDto> getTopicById(
      @PathVariable("id") Long id
  ) {
    TopicDto topic = this.topicService.getTopicById(id);

    return ResponseEntity.ok(topic);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TopicDto> updateTopicById(
      @PathVariable("id") Long id,
      @RequestBody @Valid TopicCreationDto newTopic
  ) {
    TopicDto topic = this.topicService.updateTopicById(id, newTopic.toEntity());

    return ResponseEntity.ok(topic);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTopic(
      @PathVariable("id") Long id
  ) {
    this.topicService.deleteTopicById(id);

    return ResponseEntity.noContent().build();
  }
}
