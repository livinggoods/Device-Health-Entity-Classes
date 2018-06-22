package org.goods.living.tech.devicehealthdb.dao;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.goods.living.tech.devicehealthdb.dao.Users;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-11T11:15:29")
@StaticMetamodel(Stats.class)
public class Stats_ { 

    public static volatile SingularAttribute<Stats, Date> createdAt;
    public static volatile SingularAttribute<Stats, String> provider;
    public static volatile SingularAttribute<Stats, Date> recordedAt;
    public static volatile SingularAttribute<Stats, Double> latitude;
    public static volatile SingularAttribute<Stats, Double> accuracy;
    public static volatile SingularAttribute<Stats, Long> id;
    public static volatile SingularAttribute<Stats, Users> userId;
    public static volatile SingularAttribute<Stats, Double> longitude;
    public static volatile SingularAttribute<Stats, Date> updatedAt;

}