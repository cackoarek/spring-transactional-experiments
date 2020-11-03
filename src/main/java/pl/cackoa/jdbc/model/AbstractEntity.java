package pl.cackoa.jdbc.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;

}
