package sk.fri.uniza.api;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Device.class)
public abstract class Device_ {

	public static volatile SingularAttribute<Device, String> baseUrl;
	public static volatile SingularAttribute<Device, String> role;
	public static volatile SingularAttribute<Device, String> name;
	public static volatile SingularAttribute<Device, String> location;
	public static volatile SingularAttribute<Device, String> secret;
	public static volatile SingularAttribute<Device, Long> id;

	public static final String BASE_URL = "baseUrl";
	public static final String ROLE = "role";
	public static final String NAME = "name";
	public static final String LOCATION = "location";
	public static final String SECRET = "secret";
	public static final String ID = "id";

}

