package com.forum.hub.service;

import com.forum.hub.entity.Course;
import com.forum.hub.repository.CourseRepository;
import com.forum.hub.repository.TopicRepository;
import com.forum.hub.service.exceptions.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
  private CourseRepository courseRepository;

  @Autowired
  public CourseService(
      CourseRepository courseRepository
  ){
    this.courseRepository = courseRepository;
  }


  public Course findCourseByName(String nameCourse) {

    return courseRepository.findByNome(nameCourse)
        .orElseThrow(CourseNotFoundException::new);
  }
}
