package com.conference.presentations.dao;

import com.conference.presentations.model.Conference;
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

public class ConferenceDaoImpl  implements ConferenceDao{
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Conference findById(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM conferences WHERE id=:id";

        Conference result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new ConferenceMapper());
        } catch (EmptyResultDataAccessException e) {
            // do nothing, return null
        }

		/*
		 * Conference result = namedParameterJdbcTemplate.queryForObject( sql, params,
		 * new BeanPropertyRowMapper<Conference>());
		 */

        return result;
    }

    @Override
    public List<Conference> findAll() {
        String sql = "SELECT * FROM conferences";
        List<Conference> result = namedParameterJdbcTemplate.query(sql, new ConferenceMapper());

        return result;
    }

    @Override
    public void save(Conference conference) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO CONFERENCES(NAME, VENUE, CONFERENCETIME, ORGANIZER, WEBSITE, EMAILS) "
                + "VALUES ( :name, :venue, :conferenceTime, :organizer, :website, :emails)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(conference), keyHolder);
        conference.setId(keyHolder.getKey().intValue());
    }

    @Override
    public void update(Conference conference) {
        String sql = "UPDATE CONFERENCES SET NAME=:name, EMAILS=:emails, " + "VENUE=:venue, CONFERENCETIME=:conferenceTime, "
                + "ORGANIZER=:organizer, WEBSITE=:website WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(conference));
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM CONFERENCES WHERE id= :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private SqlParameterSource getSqlParameterByModel(Conference conference) {

        // Unable to handle List<String> or Array
        // BeanPropertySqlParameterSource

        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", conference.getId());
        paramSource.addValue("name", conference.getName());
        paramSource.addValue("emails", conference.getEmails());
        paramSource.addValue("venue", conference.getVenue());
        paramSource.addValue("organizer", conference.getOrganizer());
        paramSource.addValue("website", conference.getWebsite());
        paramSource.addValue("conferenceTime", conference.getConferenceTime());

        return paramSource;
    }

    private static final class ConferenceMapper implements RowMapper<Conference> {

        public Conference mapRow(ResultSet rs, int rowNum) throws SQLException {
            Conference conference = new Conference();
            conference.setId(rs.getInt("id"));
            conference.setName(rs.getString("name"));
            conference.setEmails(rs.getString("emails"));
            conference.setConferenceTime(rs.getString("conferenceTime"));
            conference.setOrganizer(rs.getString("organizer"));
            conference.setWebsite(rs.getString("website"));
            conference.setVenue(rs.getString("venue"));
            return conference;
        }
    }
}
