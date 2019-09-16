package ua.krasun.conference_portal_servlet.model.dao.mapper;

import ua.krasun.conference_portal_servlet.model.entity.Presentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class PresentationMapper implements ObjectMapper<Presentation> {
    @Override
    public Presentation extractFromResultSet(ResultSet rs) throws SQLException {
        UserMapper userMapper = new UserMapper();
        return Presentation.builder()
                .id(rs.getLong(5))
                .theme(rs.getString("theme"))
                .author(userMapper.extractFromResultSet(rs))
                .build();
    }

    @Override
    public Presentation makeUnique(Map<Long, Presentation> cache, Presentation presentation) {
        cache.putIfAbsent(presentation.getId(), presentation);
        return cache.get(presentation.getId());
    }
}
