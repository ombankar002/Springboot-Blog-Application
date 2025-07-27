package org.studyeasy.SpringBlog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority {
    
    @Id
    private Long id;

    private String name;

    @Override
    public String toString() {
        return "Authority [id=" + id + ", name=" + name + "]";
    }

    
}
