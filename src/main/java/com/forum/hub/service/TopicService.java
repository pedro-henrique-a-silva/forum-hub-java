package com.forum.hub.service;

import com.forum.hub.controller.dto.TopicDto;
import com.forum.hub.entity.Course;
import com.forum.hub.entity.Topic;
import com.forum.hub.entity.User;
import com.forum.hub.repository.TopicRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
  private TopicRepository topicRepository;
  private UserService userService;
  private CourseService courseService;

  @Autowired
  public TopicService(
      TopicRepository topicRepository,
      UserService userService,
      CourseService courseService
  ){
    this.topicRepository = topicRepository;
    this.userService = userService;
    this.courseService = courseService;
  }

  public TopicDto createTopic(Topic topic) {
    User user = userService.findUserByName(topic.getNameAutor());

    Course course = courseService.findCourseByName(topic.getNameCourse());

    topic.setAutorTopic(user);
    topic.setCourseTopic(course);
    topic.setTopicState(true);
    topic.setCreatedAt(LocalDate.now());

    Topic newTopic = topicRepository.save(topic);

    return TopicDto.fromEntity(newTopic);
  }

  public List<TopicDto> getAllTopics() {
    return topicRepository
        .findAll()
        .stream()
        .map(TopicDto::fromEntity).toList();
  }
}
