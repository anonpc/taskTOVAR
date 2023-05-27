package org.example.dao.DATABASE;

import org.example.annotation.Inject;
import org.example.annotation.Injectable;
import org.example.connection.ConnectionFactory;
import org.example.dao.ItemDao;
import org.example.factory.BeanFactory;
import org.example.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Injectable
public class ItemDaoDB implements ItemDao {
    @Inject
    private ConnectionFactory cf;

    private static final String TABLE_NAME = "items";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String COST = "cost";
    private static final String DEPARTMENT = "department_id";

    private void addResult(List<Item> answer, PreparedStatement ps) throws SQLException {
        ResultSet r = ps.executeQuery();
        if (!r.isAfterLast()) {
            while (r.next()) {
                Long id = r.getLong(1);
                String name = r.getString(2);
                int cost = r.getInt(3);
                Long department = r.getLong(4);
                answer.add(BeanFactory.getInstance().getBean(Item.class));
            }
        }
    }

    @Override
    public Item get(Long id) {
        List<Item> answer = new ArrayList<>();
        final String query = String.format("SELECT * From %s WHERE %s = ?", TABLE_NAME, ID);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, id);
            addResult(answer, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer.size() > 0 ? answer.get(0) : null;
    }

    @Override
    public boolean create(Item entity) {
        final String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES(?,?,?)", TABLE_NAME, NAME, COST, DEPARTMENT);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCost());
            ps.setLong(3, entity.getDepartmentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Item item) {
        final String query = String.format("UPDATE %s SET %s = ?, %s = ? WHERE %s = ?", TABLE_NAME, NAME, COST, DEPARTMENT, ID);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, item.getName());
            ps.setInt(2, item.getCost());
            ps.setLong(3, item.getDepartmentId());
            ps.setLong(4, item.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> answer = new ArrayList<>();
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
    public List<Item> find(String name) {
        List<Item> answer = new ArrayList<>();
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
    public boolean removeItem(Item item) {
        final String query = String.format("DELETE FROM %s WHERE %s = ?", TABLE_NAME, ID);
        try (Connection connection = cf.create()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setLong(1, item.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
