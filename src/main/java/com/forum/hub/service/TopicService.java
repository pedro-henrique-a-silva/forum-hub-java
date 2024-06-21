package com.forum.hub.service;

import com.forum.hub.controller.dto.TopicDto;
import com.forum.hub.entity.Course;
import com.forum.hub.entity.Topic;
import com.forum.hub.entity.User;
import com.forum.hub.repository.TopicRepository;
import com.forum.hub.service.exceptions.TopicAlreadyExistsException;
import com.forum.hub.service.exceptions.TopicNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    boolean topicExists = this.topicRepository.findByMensagem(topic.getMensagem())
        .isPresent();
    if (topicExists) throw new TopicAlreadyExistsException();

    User user = userService.findUserByName(topic.getNameAutor());

    Course course = courseService.findCourseByName(topic.getNameCourse());

    topic.setAutorTopic(user);
    topic.setCourseTopic(course);
    topic.setTopicState(true);
    topic.setCreatedAt(LocalDate.now());


    Topic newTopic = topicRepository.save(topic);

    return TopicDto.fromEntity(newTopic);
  }

  public List<TopicDto> getAllTopics(Pageable pageable) {
    Page<Topic> topics = topicRepository
        .findAll(pageable);
    return topics.stream()
            .map(TopicDto::fromEntity).toList();

  }

  public TopicDto getTopicById(Long id) {
    Topic topic = this.topicRepository.findById(id)
        .orElseThrow(TopicNotFoundException::new);

    return TopicDto.fromEntity(topic);
  }


  public TopicDto updateTopicById(Long id, Topic newTopic) {
    Topic topic = this.topicRepository.findById(id)
        .orElseThrow(TopicNotFoundException::new);

    User user = userService.findUserByName(newTopic.getNameAutor());

    Course course = courseService.findCourseByName(newTopic.getNameCourse());

    topic.setTitulo(newTopic.getTitulo());
    topic.setMensagem(newTopic.getMensagem());
    topic.setAutorTopic(user);
    topic.setCourseTopic(course);

    Topic updatedTopic = topicRepository.save(topic);

    return TopicDto.fromEntity(updatedTopic);

  }

  public void deleteTopicById(Long id) {
    this.getTopicById(id);

    this.topicRepository.deleteById(id);
  }
}
