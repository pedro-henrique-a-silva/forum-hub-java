package com.forum.hub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String titulo;
  private String mensagem;

  @Column(name = "data_de_criacao", columnDefinition = "DATE DEFAULT(CURRENT_DATE)")
  private LocalDate createdAt;

  @Column(name = "estado_do_topico", columnDefinition = "tinyint DEFAULT(1)")
  private Boolean topicState;

  @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "autor")
  private User autorTopic;

  @Transient
  private String nameAutor;

  @ManyToOne(targetEntity = Course.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "curso")
  private Course courseTopic;

  @Transient
  private String nameCourse;

  @OneToMany(mappedBy = "answerTopic")
  private List<Answer> topicAnswers;

}
