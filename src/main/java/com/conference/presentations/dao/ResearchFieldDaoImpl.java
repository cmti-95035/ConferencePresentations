package com.conference.presentations.dao;

import com.conference.presentations.model.ResearchField;
import com.conference.presentations.model.ResearchField;
import com.conference.presentations.server.ResearchFieldArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResearchFieldDaoImpl implements ResearchFieldDao {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public ResearchField findById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM researchFields WHERE id=:id";

        ResearchField result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new ResearchFieldMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

		/*
		 * ResearchField result = namedParameterJdbcTemplate.queryForObject( sql, params,
		 * new BeanPropertyRowMapper<ResearchField>());
		 */

        return result;
    }

    @Override
    public List<ResearchField> findAll() {
//        String sql = "SELECT * FROM researchFields";
//        List<ResearchField> result = namedParameterJdbcTemplate.query(sql, new ResearchFieldMapper());
//
//        return result;

        ResearchFieldArray researchFieldArray = ConferenceRestServer.getAllFields();
        List<ResearchField> result = new ArrayList<>();
        for(com.conference.presentations.server.ResearchField researchField : researchFieldArray){
            result.add(toModelResearchField(researchField));
        }

        return result;
    }


    private SqlParameterSource getSqlParameterByModel(ResearchField researchField) {

        // Unable to handle List<String> or Array
        // BeanPropertySqlParameterSource

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", researchField.getId());
        paramSource.addValue("name", researchField.getName());

        return paramSource;
    }

    @Override
    public Integer findByField(String field) {
        Map<String, Object> params = new HashMap<>();
        params.put("field", field);

        String sql = "SELECT * FROM researchFields WHERE field=:field";

        ResearchField result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new ResearchFieldMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

		/*
		 * ResearchField result = namedParameterJdbcTemplate.queryForObject( sql, params,
		 * new BeanPropertyRowMapper<ResearchField>());
		 */

        return result == null ? null : result.getId();
    }

    private static final class ResearchFieldMapper implements RowMapper<ResearchField> {

        public ResearchField mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResearchField user = new ResearchField();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));

            return user;
        }
    }

    private com.conference.presentations.server.ResearchField toServerField(ResearchField researchField){
        return new com.conference.presentations.server.ResearchField().setFieldName(researchField.getName()).setId(researchField.getId());
    }

    private ResearchField toModelResearchField(com.conference.presentations.server.ResearchField researchField){
        ResearchField rf = new ResearchField();
        rf.setId(researchField.getId());
        rf.setName(researchField.getFieldName());

        return rf;
    }

}
