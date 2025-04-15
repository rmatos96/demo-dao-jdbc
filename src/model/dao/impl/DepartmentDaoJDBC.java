package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department ojb) {

		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("insert into department (Name) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, ojb.getName());

			int linhasAfetadas = st.executeUpdate();
			if (linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					ojb.setId(id);
				} else
					throw new DbException("Nenhuma linha afetada");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department ojb) {

		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update department set Name = ? where Id = ?");
			statement.setString(1, ojb.getName());
			statement.setInt(2, ojb.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from department where Id = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
		}
	}

	@Override
	public Department findById(Integer id) {

		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from department where Id = ?");
			statement.setInt(1, id);

			rs = statement.executeQuery();
			if (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				return dep;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			statement = conn.prepareStatement("select * from department order by Id");
			rs = statement.executeQuery();

			List<Department> list = new ArrayList<Department>();

			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(statement);
			DB.closeResultSet(rs);
		}

	}
}
