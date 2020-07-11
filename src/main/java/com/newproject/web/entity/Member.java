package com.newproject.web.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Member {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;

    public Member(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt(1);
            this.username = resultSet.getString(2);
            this.password = resultSet.getString(3);
            this.name = resultSet.getString(4);
            this.email = resultSet.getString(5);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
