package ua.krasun.conference_portal_servlet.model.dao.mapper;

import ua.krasun.conference_portal_servlet.model.entity.Conference;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ConferenceMapper implements ObjectMapper<Conference> {
    @Override
    public Conference extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper userMapper = new UserMapper();
        return Conference.builder()
                .id(rs.getLong("conference_id"))
                .date(rs.getDate("date").toLocalDate())
                .subject(rs.getString("subject"))
                .author(userMapper.extractFromResultSet(rs))
                .build();
    }

    @Override
    public Conference makeUnique(Map<Long, Conference> cache, Conference conference) {
        cache.putIfAbsent(conference.getId(), conference);
        return cache.get(conference.getId());
    }
}
