package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.annotation.Injectable;

@Getter
@Setter
@Injectable
public class Entity {
    private Long id;
}
