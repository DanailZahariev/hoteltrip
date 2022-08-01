package bg.hoteltrip.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

    private String tittle;

    private String url;

    private String publicId;

    @ManyToMany(mappedBy = "hotelPictures")
    private List<HotelEntity> hotels;

    public String getTittle() {
        return tittle;
    }

    public PictureEntity setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPublicId() {
        return publicId;
    }

    public PictureEntity setPublicId(String publicId) {
        this.publicId = publicId;
        return this;
    }
}
