package com.forum.hub.controller;

import com.forum.hub.controller.dto.TopicCreationDto;
import com.forum.hub.controller.dto.TopicDto;
import com.forum.hub.entity.Topic;
import com.forum.hub.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    return ResponseEntity.ok(createdTopic);
  }

  @GetMapping
  public ResponseEntity<List<TopicDto>> getAllTopics() {
    List<TopicDto> topics = topicService.getAllTopics();

    return ResponseEntity.ok(topics);
  }
}
