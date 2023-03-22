package com.example.LabWorkMarch.dao;

import com.example.LabWorkMarch.entity.Event;
import com.example.LabWorkMarch.entity.Subscription;
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
public class SubscriptionDao extends BaseDao{
    public SubscriptionDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public void createTable() {
        jdbcTemplate.execute("create table if not exists subscription\n" +
                "(\n" +
                "    id bigserial primary key,\n" +
                "    eventID bigserial,\n" +
                "    foreign key (eventID) references events (id),\n" +
                "    email  varchar not null,\n" +
                "    dateOfRegistration  DATE not null\n" +
                ");");
    }

    public List<Subscription> findSubscriptionByEmail(String email){
        String sql = "select * " +
                "from subscription " +
                "where email = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Subscription.class),email);
    }
    public void cancelSubscription(Long eventID, String email){
        String sql = "delete from subscription " +
                "where eventID = ? " +
                "and email = ?";
        jdbcTemplate.update(sql,eventID,email);
    }


    public void add(Subscription subscription) {
        String sql = "insert into subscription(eventID, email, dateOfRegistration) " +
                "values(?,?,?)";
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, subscription.getEventID() );
            ps.setString(2, subscription.getEmail());
            ps.setDate(3, Date.valueOf(subscription.getDateOfRegistration()));
            return ps;
        });
    }
    public void deleteAll(){
        String sql = "delete from subscription";
        jdbcTemplate.update(sql);
    }
}
