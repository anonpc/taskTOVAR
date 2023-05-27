package org.example.dao;

import org.example.models.Department;

import java.util.List;

public interface DepartmentDao extends Dao<Department> {
    List<Department> getAllDepartment();
    List<Department> find(String name);

    boolean removeDepartment(Department department);

}
