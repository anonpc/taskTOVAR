package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.annotation.Inject;
import org.example.annotation.Injectable;
import org.example.dao.DepartmentDao;
import org.example.models.Department;

import java.util.List;

@Injectable
public class DepartmentController {
    @Inject
    private DepartmentDao departmentDao;

    public void addNewDepartment(Department d) {
        departmentDao.create(d);
    }

    public boolean removeDepartment(Department d) {
        return departmentDao.removeDepartment(d);
    }

    public List<Department> getAllDepartment() {
        return departmentDao.getAllDepartment();
    }

    public List<Department> findDepartmentByName(String name) {
        return departmentDao.find(name);
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

}
