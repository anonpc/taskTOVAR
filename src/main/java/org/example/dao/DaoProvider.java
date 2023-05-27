package org.example.dao;

public interface DaoProvider {
    public ItemDao getItemDao();
    public DepartmentDao getDepartmentDao();
}
