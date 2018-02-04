package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Organization {
    private Long id;
    private String name;
    private String placement;
    private Date date;
}
