package bg.hoteltrip.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "towns")
public class TownEntity extends BaseEntity {

    @Column(name = "town_name",nullable = false, unique = true)
    private String townName;


    public TownEntity() {
    }

    public String getTownName() {
        return townName;
    }

    public TownEntity setTownName(String townName) {
        this.townName = townName;
        return this;
    }
}
