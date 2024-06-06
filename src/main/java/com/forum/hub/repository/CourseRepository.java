package com.forum.hub.repository;

import com.forum.hub.entity.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findByNome(String nameCourse);
}
