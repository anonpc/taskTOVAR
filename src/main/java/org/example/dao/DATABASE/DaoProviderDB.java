package org.example.dao.DATABASE;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.connection.ConnectionFactory;
import org.example.dao.DaoProvider;
import org.example.dao.DepartmentDao;
import org.example.dao.ItemDao;

@RequiredArgsConstructor
@Getter
public class DaoProviderDB implements DaoProvider {
   private final DepartmentDao departmentDao;
   private final ItemDao itemDao;
}
