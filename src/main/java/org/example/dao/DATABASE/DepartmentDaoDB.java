package org.example.dao.DATABASE;

import lombok.AllArgsConstructor;
import org.example.annotation.Inject;
import org.example.annotation.Injectable;
import org.example.connection.ConnectionFactory;
import org.example.dao.DepartmentDao;
import org.example.factory.BeanFactory;
import org.example.models.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Injectable
public class DepartmentDaoDB implements DepartmentDao {
    @Inject
    private ConnectionFactory cf;
    private static final String TABLE_NAME = "departments";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String TIME_WORK = "time_work";

    private void addResult(List<Department> answer, PreparedStatement ps) throws SQLException {
        ResultSet r = ps.executeQuery();
        if (!r.isAfterLast()) {
            while (r.next()) {
                Long id = r.getLong(1);
                String name = r.getString(2);
                String timeWork = r.getString(3);
                answer.add(BeanFactory.getInstance().getBean(Department.class));
            }
        }
    }

    @Override
    public List<Department> getAllDepartment() {
        List<Department> answer = new ArrayList<>();
        final String query = String.format("SELECT * FROM %s", TABLE_NAME);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            addResult(answer, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    @Override
    public Department get(Long id) {
        List<Department> answer = new ArrayList<>();
        final String query = String.format("SELECT * From %s WHERE %s = ?", TABLE_NAME, ID);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(2, id);
            addResult(answer, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer.size() > 0 ? answer.get(0) : null;
    }

    @Override
    public boolean create(Department entity) {
        final String query = String.format("INSERT INTO %s (%s, %s) VALUES(?,?)", TABLE_NAME, NAME, TIME_WORK);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getTimeWork());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Department department) {
        final String query = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME, NAME, TIME_WORK);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, department.getName());
            ps.setString(2, department.getTimeWork());
            ps.setLong(3, department.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> find(String name) {
        List<Department> answer = new ArrayList<>();
        final String query = String.format("SELECT * from %s WHERE %s = ?", TABLE_NAME, NAME);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            addResult(answer, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (answer.size() > 0) {
            return answer;
        }
        return null;
    }

    @Override
    public boolean removeDepartment(Department department) {
        final String query = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, ID);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, department.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
