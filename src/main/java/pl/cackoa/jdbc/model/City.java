package pl.cackoa.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "city")
public class City extends AbstractEntity {

    public City(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    String name;
}
