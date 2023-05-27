package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.annotation.Injectable;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Injectable
public class Department extends Entity {

    private String name;
    private String timeWork;

    public String toString() {
        return "Department{" +
                "name=" + name + '\'' +
                ", timeWork='" + timeWork +
                '}';
    }
}
