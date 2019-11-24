package com.sergiu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "halls")
public class HallEntity implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private int size;

    @Column(name = "utilizable_size")
    private int utilizableSize;

    @OneToMany
    @JoinTable(name = "distribution", joinColumns = {@JoinColumn(name = "id_hall")}, inverseJoinColumns = {
            @JoinColumn(name = "cnp_candidate")})
    private List<CandidateEntity> listCandidates;


    public HallEntity() {
        this.listCandidates = new ArrayList<>();
    }

    public HallEntity(String name, int size, int utilizableSize) {
        this.name = name;
        this.size = size;
        this.utilizableSize = utilizableSize;
        this.listCandidates = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUtilizableSize() {
        return utilizableSize - getListCandidates().size();
    }

    public void setUtilizableSize(int utilizableSize) {
        this.utilizableSize = utilizableSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<CandidateEntity> getListCandidates() {
        return listCandidates;
    }

    public void setListCandidates(List<CandidateEntity> listCandidates) {
        this.listCandidates = listCandidates;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + size;
        result = prime * result + utilizableSize;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HallEntity other = (HallEntity) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (size != other.size)
            return false;
        if (utilizableSize != other.utilizableSize)
            return false;
        return true;
    }

    @Override
    public int compareTo(Object o) {
        HallEntity hallEntity = (HallEntity) o;

        return (this.utilizableSize - listCandidates.size()) -
                (hallEntity.getUtilizableSize() - hallEntity.getListCandidates().size());
    }
}
