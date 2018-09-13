package com.training.spring.bigcorp.repository;

import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Site;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SiteDaoImpl implements SiteDao {

    private static String SELECT_BY_ID = "SELECT s.name as site_name FROM Site";
    private static String SELECT_All = "SELECT s.name as site_name FROM Site";

    private NamedParameterJdbcTemplate jdbcTemplate;

    public SiteDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Site element) {
        jdbcTemplate.update("INSERT INTO SITE (ID, NAME) VALUES(set name = :name)",
                new MapSqlParameterSource()
                        .addValue("id", element.getName()));
    }

    @Override
    public Site findById(String s) {
        jdbcTemplate.update(SELECT_BY_ID,
                new MapSqlParameterSource()
                        .addValue("name", s));
        return
    }


    @Override
    public List<Site> findAll() {
        return jdbcTemplate.query(SELECT_All, this::siteMapper);
    }

    private Site siteMapperById(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("site_name"));
        site.setId(rs.getString("site_id"));
        return site;
    }

    private Site siteMapper(ResultSet rs, int rowNum) throws SQLException {
        Site site = new Site(rs.getString("site_name"));
        site.setId(rs.getString("site_id"));
        return site;
    }













    @Override
    public void update(Site element) {
        jdbcTemplate.update("update SITE set name = :name where id =:id",
                new MapSqlParameterSource()
                        .addValue("id", element.getId())
                        .addValue("name", element.getName()));
    }

    @Override
    public void deleteById(String s) {
        jdbcTemplate.update("DELETE * FROM SITE WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("id", s));
    }
}