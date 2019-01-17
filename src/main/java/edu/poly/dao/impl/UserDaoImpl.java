package edu.poly.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.poly.dao.UserDao;
import edu.poly.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addUser(User user) {
		String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE user SET username = ?, password = ? WHERE id = ?";
		jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getId());
	}

	@Override
	public void deleteUser(int id) {
		String sql = "DELETE FROM user WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public User getUserById(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";

		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));

				return user;
			}

		});
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM user";

		return jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));

				return user;
			}

		});
	}

}
