package com.conference.presentations.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.conference.presentations.server.ResearchField;
import com.conference.presentations.server.UserArray;
import com.linkedin.data.template.GetMode;
import com.linkedin.data.template.IntegerArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.conference.presentations.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private static HashMap<String, Integer> fieldMap = new HashMap<>();
	private static HashMap<Integer, String> fieldIdMap = new HashMap<>();

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public User findById(Integer id) {

//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("id", id);
//
//		String sql = "SELECT * FROM users WHERE id=:id";
//
//		User result = null;
//		try {
//			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
//		} catch (EmptyResultDataAccessException e) {
//			// do nothing, return null
//		}
//
//		/*
//		 * User result = namedParameterJdbcTemplate.queryForObject( sql, params,
//		 * new BeanPropertyRowMapper<User>());
//		 */
//
//		return result;

		return toModelUser(ConferenceRestServer.getUser(id));

	}

	@Override
	public List<User> findAll() {
//
//		String sql = "SELECT * FROM users";
//		List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());
//
//		return result;

		UserArray userArray = ConferenceRestServer.getAllUsers();
		List<User> result = new ArrayList<>();
		for(com.conference.presentations.server.User user : userArray){
			result.add(toModelUser(user));
		}

		return result;
	}

	@Override
	public void save(User user) {

//		KeyHolder keyHolder = new GeneratedKeyHolder();
//
//		String sql = "INSERT INTO USERS(NAME, EMAIL, PASSWORD, PHONENUMBER, FIELDS, COUNTRY, SEX, ADDRESS) "
//				+ "VALUES ( :name, :email, :password, :phoneNumber, :fields, :country, :sex, :address)";
//
//		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);

		Integer userId = ConferenceRestServer.createUser(user.getEmail(), user.getName(), user.getPassword(), user.getAddress(), user.getCountry(), user.getPhoneNumber(), convertFieldNameToFieldIdFromList(user.getFields()));
//		user.setId(keyHolder.getKey().intValue());
		user.setId(userId);
	}

	@Override
	public void update(User user) {

//		String sql = "UPDATE USERS SET NAME=:name, EMAIL=:email, " + "PASSWORD=:password, FIELDS=:fields, "
//				+ "PHONENUMBER=:phoneNumber, COUNTRY=:country, " + " SEX=:sex, ADDRESS=:address WHERE id=:id";
//
//		namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
		ConferenceRestServer.updateUser(toServerUser(user), user.getId());
	}

	@Override
	public void delete(Integer id) {

//		String sql = "DELETE FROM USERS WHERE id= :id";
//		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
		ConferenceRestServer.deleteUser(id);
	}

	private SqlParameterSource getSqlParameterByModel(User user) {

		// Unable to handle List<String> or Array
		// BeanPropertySqlParameterSource

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
		paramSource.addValue("password", user.getPassword());

		// join String
		paramSource.addValue("fields", convertListToDelimitedString(user.getFields()));
		paramSource.addValue("phoneNumber", user.getPhoneNumber());
		paramSource.addValue("country", user.getCountry());

		return paramSource;
	}

//	private static final class UserMapper implements RowMapper<User> {
//
//		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//			User user = new User();
//			user.setId(rs.getInt("id"));
//			user.setName(rs.getNString("name"));
//			user.setEmail(rs.getNString("email"));
//			user.setFields(convertDelimitedStringToList(rs.getNString("fields")));
//			user.setCountry(rs.getString("country"));
//			user.setPhoneNumber(rs.getNString("phoneNumber"));
//			user.setPassword(rs.getNString("password"));
//			user.setAddress(rs.getNString("address"));
//			return user;
//		}
//	}

	private static List<String> convertDelimitedStringToList(String delimitedString) {

		List<String> result = new ArrayList<String>();

		if (!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;

	}

	private List<Integer> convertFieldNameToFieldId(String fields){
		if(fields == null || fields.isEmpty())
			return new ArrayList<>();

		List<String> fieldNames = convertDelimitedStringToList(fields);

		return convertFieldNameToFieldIdFromList(fieldNames);
	}

	private List<Integer> convertFieldNameToFieldIdFromList(List<String> fieldNames){
		if(fieldMap.isEmpty()){
			List<ResearchField> fieldList = ConferenceRestServer.getAllFields();
			for(ResearchField field : fieldList){
				fieldMap.put(field.getFieldName(), field.getId());
			}
		}

		List<Integer> fieldIds = new ArrayList<>();
		for(String fieldName: fieldNames){
			if(fieldMap.containsKey(fieldName))
				fieldIds.add(fieldMap.get(fieldName));
		}

		return fieldIds;
	}

	private List<String> convertFieldIdToFieldNameFromList(List<Integer> fieldIds ){
		if(fieldIdMap.isEmpty()){
			List<ResearchField> fieldList = ConferenceRestServer.getAllFields();
			for(ResearchField field : fieldList){
				fieldIdMap.put(field.getId(), field.getFieldName());
			}
		}

		List<String> fieldNames = new ArrayList<>();
		for(Integer fieldId : fieldIds){
			if(fieldIdMap.containsKey(fieldId))
				fieldNames.add(fieldIdMap.get(fieldId));
		}

		return fieldNames;
	}

	private String convertListToDelimitedString(List<String> list) {

		String result = "";
		if (list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		return result;

	}

	private com.conference.presentations.server.User toServerUser(User user)
	{
		return new com.conference.presentations.server.User()
				.setPassword(user.getPassword())
				.setCountry(user.getCountry())
				.setAddress(user.getAddress())
				.setEmail(user.getEmail())
				.setName(user.getName())
				.setFields(new IntegerArray(convertFieldNameToFieldIdFromList(user.getFields())))
				.setId(user.getId());
	}

	private User toModelUser(com.conference.presentations.server.User user) {
		User result = new User();
		result.setAddress(user.getAddress(GetMode.NULL));
		result.setEmail(user.getEmail());
		result.setCountry(user.getCountry(GetMode.NULL));
		result.setName(user.getName());
		result.setPassword(user.getPassword());
		result.setPhoneNumber(user.getPhoneNumber(GetMode.NULL));
		result.setId(user.getId());
		result.setFields(convertFieldIdToFieldNameFromList(user.getFields()));

		return result;
	}
}
