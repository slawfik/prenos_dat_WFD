package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import sk.fri.uniza.api.Paged;
import sk.fri.uniza.openweathermap.LiteWeatherOBJ;

import java.util.List;
import java.util.Optional;

public class LiteWeatherObjDao extends AbstractDAO<LiteWeatherOBJ> implements BasicDao<LiteWeatherOBJ, Long>{
    private static LiteWeatherObjDao weather_dao;

    public LiteWeatherObjDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public static LiteWeatherObjDao createWeatherDao(SessionFactory sessionFactory) {
        if (weather_dao == null)
            weather_dao = new LiteWeatherObjDao(sessionFactory);
        return weather_dao;
    }

    public LiteWeatherOBJ findDeviceWithName_DB(String pa_name) {
        Query query = super.currentSession().getSession().createQuery("from LiteWeatherOBJ where name = :name ");
        query.setString("name", pa_name);

        List<?> list = query.list();
        return (LiteWeatherOBJ) list.get(0);
    }

    @Override
    public List<LiteWeatherOBJ> getAll() {
        List<LiteWeatherOBJ> list = super.currentSession().createQuery("from LiteWeatherOBJ ").list();
        return list;
    }

    @Override
    public Optional<LiteWeatherOBJ> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(get(id));
    }

    @Override
    public Long save(LiteWeatherOBJ wet) throws HibernateException {
        super.persist(wet);
        return wet.getId();
    }

    @Override
    public Paged<List<LiteWeatherOBJ>> getAll(int limit, int page) {
        return null;
    }

    public Long update(LiteWeatherOBJ dev, String[] params) {
        return null;
    }

    @Override
    public void delete(LiteWeatherOBJ dev) {
        currentSession().delete(dev);
    }

}
