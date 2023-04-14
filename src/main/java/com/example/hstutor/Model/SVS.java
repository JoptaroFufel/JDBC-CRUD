package com.example.hstutor.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "svs")
public class SVS {
    @EmbeddedId
    private SVSPK pk;

    public SVS() {
    }

    public SVSPK getPk() {
        return pk;
    }

    public void setPk(SVSPK pk) {
        this.pk = pk;
    }
}
