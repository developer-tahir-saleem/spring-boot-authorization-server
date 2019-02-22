package com.tahir.authorization.server.authorizationserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@DiscriminatorValue("childClasss")
@ToString
public class Role extends BaseModel{

    private String name;

}
