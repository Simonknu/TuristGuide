package com.example.turistguide.repository;

import com.example.turistguide.model.Attraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TouristRepository {


    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(@Value("${spring.datasource.url}")
                           String dbUrl,
                             @Value("${spring.datasource.username}")
                           String username,
                             @Value("${spring.datasource.password}")
                           String password
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dbUrl);             // Use injected dbUrl directly
        dataSource.setUsername(username);     // Use injected username directly
        dataSource.setPassword(password);     // Use injected password directly
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Initialize JdbcTemplate with the configured DataSource
        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }


    public List<Attraction> getAllTouristAttractions() {
        String sql = "SELECT a.name, a.description, c.city_name " +
                "FROM attractions a " +
                "JOIN cities c ON a.city_id = c.id";
        return jdbcTemplate.query(sql, new AttractionRowMapper());
    }


    public List<String> cities() {
        String sql = "SELECT city_name FROM cities";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    public List<String> tags() {
        String sql = "SELECT tag_name FROM tags";
        return jdbcTemplate.queryForList(sql, String.class);
    }


    public class AttractionRowMapper implements RowMapper<Attraction> {
        @Override
        public Attraction mapRow(ResultSet rs, int rowNum) throws SQLException {
            String name = rs.getString("name");
            String description = rs.getString("description");
            String city = rs.getString("city_name");

            List<String> tags = getTagsForAttractionByName(name);
            // Return the Attraction object with tags
            return new Attraction(name, description, city, tags);
        }

    }

    public List<String> getTagsForAttractionByName(String attractionName) {
        String sql = "SELECT t.tag_name " +
                "FROM tags t " +
                "JOIN attraction_tags at ON t.id = at.tag_id " +
                "JOIN attractions a ON a.id = at.attraction_id " +
                "WHERE a.name = ?";
        return jdbcTemplate.queryForList(sql, String.class, attractionName);
    }

    public Attraction getTouristAttractionByName(String name) {
        List<Attraction> attractions = getAllTouristAttractions();
        for (Attraction a : attractions) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    public List<Attraction> getAttractions() {
        return getAllTouristAttractions();
    }

    public List<String> getCities() {
        return cities();
    }

    public List<String> getTags() {
        return tags();
    }


    public void deleteTouristAttraction(Attraction attraction) {
        String sql = "DELETE FROM attractions WHERE name = ?";
        jdbcTemplate.update(sql, attraction.getName());

    }

    public void createAttraction(String name, String description, String city, List<String> tags) {
        String sql = "INSERT INTO attractions  (name, description, city_id) VALUES (?,?,?)";
        jdbcTemplate.update(sql, name, description, cityToId(city));

        String sqlGetAttractionId = "SELECT LAST_INSERT_ID()";  // This retrieves the last inserted ID
        int setAttractionId = jdbcTemplate.queryForObject(sqlGetAttractionId, Integer.class);

        String sqlAttractionId = "SELECT id FROM attractions WHERE name = ?";
        Integer attractionId = jdbcTemplate.queryForObject(sqlAttractionId, Integer.class, name);

        for (String tagName : tags) {
            String sqlTagId = "SELECT id FROM tags WHERE tag_name = ?";
            Integer tagId = jdbcTemplate.queryForObject(sqlTagId, Integer.class, tagName);

            String sqlInsert = "INSERT IGNORE INTO attraction_tags (attraction_id, tag_id) VALUES (?, ?)";
            jdbcTemplate.update(sqlInsert, attractionId, tagId);
        }

    }

    public void updateAttraction(String name, String newName, String description, String city, List<String> tags) {
        String sql = "UPDATE attractions SET name = ?, description = ?, city_id = ? WHERE name = ?";


        String sqlAttractionId = "SELECT id FROM attractions WHERE name = ?";
        Integer attractionId = jdbcTemplate.queryForObject(sqlAttractionId, Integer.class, name);

        for (String tagName : tags) {
            String sqlTagId = "SELECT id FROM tags WHERE tag_name = ?";
            Integer tagId = jdbcTemplate.queryForObject(sqlTagId, Integer.class, tagName);

            String sqlInsert = "INSERT IGNORE INTO attraction_tags (attraction_id, tag_id) VALUES (?, ?)";
            jdbcTemplate.update(sqlInsert, attractionId, tagId);
        }


        // Updating the attraction details in the 'attractions' table
        jdbcTemplate.update(sql, newName, description, cityToId(city), name);

    }


    private int cityToId(String cityName) {
        String sql = "SELECT id FROM cities WHERE city_name = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cityName}, Integer.class);
    }

}



