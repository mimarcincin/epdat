package sk.upjs.epdat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "records")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private int endopolyploidy;

    private String chromosomeNumber;

    private int ploidyLevel;

    private int number;

    private String indexType;

    private String tissue;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Plant plant;

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEndopolyploidy() {
        return endopolyploidy;
    }

    public void setEndopolyploidy(int endopolyploidy) {
        this.endopolyploidy = endopolyploidy;
    }

    public String getChromosomeNumber() {
        return chromosomeNumber;
    }

    public void setChromosomeNumber(String chromosomeNumber) {
        this.chromosomeNumber = chromosomeNumber;
    }

    public int getPloidyLevel() {
        return ploidyLevel;
    }

    public void setPloidyLevel(int ploidyLevel) {
        this.ploidyLevel = ploidyLevel;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
