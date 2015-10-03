package com.conference.presentations.dao;

import com.conference.presentations.model.Conference;
import com.conference.presentations.server.ResearchField;
import com.linkedin.data.template.IntegerArray;
import com.linkedin.data.template.SetMode;
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

public class ConferenceDaoImpl  implements ConferenceDao{
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static HashMap<String, Integer> fieldMap = new HashMap<>();
    private static HashMap<Integer, String> fieldIdMap = new HashMap<>();

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Conference findById(Integer id) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", id);
//
//        String sql = "SELECT * FROM conferences WHERE id=:id";
//
//        Conference result = null;
//        try {
//            result = namedParameterJdbcTemplate.queryForObject(sql, params, new ConferenceMapper());
//        } catch (EmptyResultDataAccessException e) {
//            // do nothing, return null
//        }

        return toModelConference(ConferenceRestServer.getConference(id));
    }

    @Override
    public List<Conference> findAll() {
//        String sql = "SELECT * FROM conferences";
        List<Conference> result = new ArrayList<>();
        List<com.conference.presentations.server.Conference> conferences = ConferenceRestServer.getAllConferences();

        for(com.conference.presentations.server.Conference c: conferences){
            result.add(toModelConference(c));
        }

        return result;
    }

    @Override
    public void save(Conference conference) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        String sql = "INSERT INTO CONFERENCES(NAME, VENUE, CONFERENCETIME, ORGANIZER, WEBSITE, EMAILS) "
//                + "VALUES ( :name, :venue, :conferenceTime, :organizer, :website, :emails)";
//
//        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(conference), keyHolder);
//        conference.setId(keyHolder.getKey().intValue());

        Integer conferenceId = ConferenceRestServer.createConference(toServerConference(conference));
        conference.setId(conferenceId);
    }

    @Override
    public void update(Conference conference) {
//        String sql = "UPDATE CONFERENCES SET NAME=:name, EMAILS=:emails, " + "VENUE=:venue, CONFERENCETIME=:conferenceTime, "
//                + "ORGANIZER=:organizer, WEBSITE=:website WHERE id=:id";
//
//        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(conference));

        ConferenceRestServer.updateConference(toServerConference(conference), conference.getId());
    }

    @Override
    public void delete(Integer id) {
//        String sql = "DELETE FROM CONFERENCES WHERE id= :id";
//        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));

        ConferenceRestServer.deleteConference(id);
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

//    private static final class ConferenceMapper implements RowMapper<Conference> {
//
//        public Conference mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Conference conference = new Conference();
//            conference.setId(rs.getInt("id"));
//            conference.setName(rs.getString("name"));
//            conference.setEmails(rs.getString("emails"));
//            conference.setConferenceTime(rs.getString("conferenceTime"));
//            conference.setOrganizer(rs.getString("organizer"));
//            conference.setWebsite(rs.getString("website"));
//            conference.setVenue(rs.getString("venue"));
//            return conference;
//        }
//    }

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

    private com.conference.presentations.server.Conference toServerConference(Conference conference){
        return new com.conference.presentations.server.Conference()
                .setConferenceTime(conference.getConferenceTime())
                .setEmails(conference.getEmails())
                .setConferenceId(conference.getId(), SetMode.IGNORE_NULL)
                .setName(conference.getName())
                .setWebsite(conference.getWebsite())
                .setVenue(conference.getVenue())
                .setOrganizer(conference.getOrganizer())
                .setFields(new IntegerArray(convertFieldNameToFieldIdFromList(conference.getFields())));
    }

    private Conference toModelConference(com.conference.presentations.server.Conference conference){
        Conference result = new Conference();
                result.setConferenceTime(conference.getConferenceTime());
                result.setEmails(conference.getEmails());
                result.setId(conference.getConferenceId());
                result.setName(conference.getName());
                result.setWebsite(conference.getWebsite());
                result.setVenue(conference.getVenue());
                result.setOrganizer(conference.getOrganizer());
                result.setFields(convertFieldIdToFieldNameFromList(conference.getFields()));

        return result;
    }
}
