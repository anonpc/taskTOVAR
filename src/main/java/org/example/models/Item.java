package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.annotation.Injectable;

@Getter
@Setter
@Injectable
public class Item extends Entity {

    private String name;
    private int cost;
    private Long departmentId;

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", DepartmentId=" + departmentId +
                '}';
    }

}
