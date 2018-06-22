package org.goods.living.tech.devicehealthdb.dao;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.goods.living.tech.devicehealthdb.dao.Stats;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-11T11:15:29")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile CollectionAttribute<Users, Stats> statsCollection;
    public static volatile SingularAttribute<Users, String> chvId;
    public static volatile SingularAttribute<Users, Boolean> disableSync;
    public static volatile SingularAttribute<Users, String> versionName;
    public static volatile SingularAttribute<Users, Integer> versionCode;
    public static volatile SingularAttribute<Users, Date> createdAt;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, Integer> updateInterval;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile SingularAttribute<Users, Date> recordedAt;
    public static volatile SingularAttribute<Users, Long> id;
    public static volatile SingularAttribute<Users, String> androidId;
    public static volatile SingularAttribute<Users, Date> updatedAt;
    public static volatile SingularAttribute<Users, String> username;

}