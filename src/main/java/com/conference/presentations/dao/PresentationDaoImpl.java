package com.conference.presentations.dao;

import com.conference.presentations.dao.PresentationDao;
import com.conference.presentations.model.Presentation;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresentationDaoImpl implements PresentationDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Presentation findById(Integer id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM presentations WHERE id=:id";

        Presentation result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new PresentationMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

		/*
		 * User result = namedParameterJdbcTemplate.queryForObject( sql, params,
		 * new BeanPropertyRowMapper<User>());
		 */

        return result;
    }

    @Override
    public List<Presentation> findAll() {
        String sql = "SELECT * FROM presentations";
        List<Presentation> result = namedParameterJdbcTemplate.query(sql, new PresentationMapper());

        return result;
    }

    @Override
    public List<Presentation> findAllByField(Integer fieldId) {
        String sql = "SELECT * FROM presentations where fieldId=:fieldId";
        List<Presentation> result = namedParameterJdbcTemplate.query(sql, new PresentationMapper());

        return result;
    }

    @Override
    public List<Presentation> findAllByConferenceId(Integer conferenceId) {
        String sql = "SELECT * FROM presentations where conferenceId=:conferenceId";
        List<Presentation> result = namedParameterJdbcTemplate.query(sql, new PresentationMapper());

        return result;
    }

    @Override
    public void save(Presentation presentation) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO presentations (userId, conferenceId, title, authors, fieldId, abs)"
                + "VALUES ( :userId, :conferenceId, :title, :authors, :fieldId, :abs)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(presentation), keyHolder);
        presentation.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Presentation presentation) {
        String sql = "UPDATE USERS SET userId=:userId, conferenceId=:conferenceId, " + "title=:title, authors=:authors, "
                + "fieldId=:fieldId, abs=:abs WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(presentation));
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM presentations WHERE id= :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private SqlParameterSource getSqlParameterByModel(Presentation presentation) {

        // Unable to handle List<String> or Array
        // BeanPropertySqlParameterSource

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", presentation.getId());
        paramSource.addValue("userId", presentation.getUserId());
        paramSource.addValue("conferenceId", presentation.getConferenceId());
        paramSource.addValue("title", presentation.getTitle());
        paramSource.addValue("abs", presentation.getAbs());
        paramSource.addValue("fieldId", presentation.getFieldId());
        paramSource.addValue("authors", presentation.getAuthors());

        return paramSource;
    }

    private static final class PresentationMapper implements RowMapper<Presentation> {

        public Presentation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Presentation presentation = new Presentation();
            presentation.setId(rs.getInt("id"));
            presentation.setUserId(rs.getInt("userId"));
            presentation.setConferenceId(rs.getInt("conferenceId"));
            presentation.setTitle(rs.getString("title"));
            presentation.setAuthors(rs.getString("authors"));
            presentation.setAbs(rs.getString("abs"));
            presentation.setFieldId(rs.getInt("fieldId"));
            return presentation;
        }
    }
}
