package com.example.LabWorkMarch.dao;

import com.example.LabWorkMarch.entity.Event;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class EventDao extends BaseDao {
    public EventDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists events\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    date  DATE not null,\n" +
                "    name  varchar not null,\n" +
                "    description  varchar\n" +
                ");");
    }

    public List<Event> findAll() {
        String sql = "select * from events";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Event.class));
    }

    public Optional<Event> findEventByID(Long id){
        String sql = "select * " +
                "from events " +
                "where id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Event.class),sql)
        ));
    }

    public void add(Event event) {
        String sql = "insert into events(date, name, description) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(event.getDate()));
            ps.setString(2, event.getName());
            ps.setString(3, event.getDescription());
            return ps;
        });
    }

    public void deleteAll(){
        String sql = "delete from events";
        jdbcTemplate.update(sql);
    }
}
