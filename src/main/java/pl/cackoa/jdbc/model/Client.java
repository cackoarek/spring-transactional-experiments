package pl.cackoa.jdbc.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "Client")
public class Client extends AbstractEntity {

  @Column(name = "first_name")
  String first_name;

  @Column(name = "last_name")
  String last_name;

  public Client(Long id, String fname, String lname) {
    super(id);
    first_name = fname;
    last_name = lname;
  }

}
