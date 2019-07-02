package sk.fri.uniza.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import sk.fri.uniza.api.Device;
import sk.fri.uniza.api.Paged;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DevicesDao extends AbstractDAO<Device> implements BasicDao<Device, Long> {
    private static DevicesDao dev_dao;
    public static HashMap<Integer, Device> devicesHashMap = new HashMap<>();

    public static DevicesDao createDevDao(SessionFactory sessionFactory) {
        if (dev_dao == null)
            dev_dao = new DevicesDao(sessionFactory);
        return dev_dao;
    }

    public DevicesDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //########START_LOCAL_DATABASE###########
    static{
        devicesHashMap.put(1, new Device("Tomas","Poprad","device123","localhost:8090/"));
        devicesHashMap.put(2, new Device("Beagle","Poprad","device123","localhost:8090/"));
        devicesHashMap.put(3, new Device("Peter","Zilina","device123","localhost:8090/"));
        devicesHashMap.put(4, new Device("Rudo","Zilina","device123","localhost:8090/"));

    }

    public List<Device> getArrayL_Devices(){
        return new ArrayList<Device>(devicesHashMap.values());
    }

    public Device getDeviceFromLocalDB_WithID(Integer id){
        return devicesHashMap.get(id);
    }

    public void updateDeviceFromLocalDB_WithID(Integer id, Device employee){
        devicesHashMap.put(id, employee);
    }

    public void removeDeviceFromLocalDB_WithID(Integer id){
        devicesHashMap.remove(id);
    }

    //########END_LOCAL_DATABASE###########

    @Override
    public List<Device> getAll() {
        List<Device> list = super.currentSession().createQuery("from Device ").list();
        return list;
    }

    public Device findDeviceWithName_DB(String pa_name) {
        Query query = super.currentSession().getSession().createQuery("from Device where D_name = :name ");
        query.setString("name", pa_name);

        List<?> list = query.list();
        return (Device) list.get(0);
    }

    @Override
    public Optional<Device> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(get(id));
    }

    public Device getDeviceWithId_DB(Integer ID)    {
        Query query = super.currentSession().getSession().createQuery("from Device where id = :id ");
        query.setInteger("id", ID);

        List<?> list = query.list();

        return (Device)list.get(0);
    }

    @Override
    public Paged<List<Device>> getAll(int limit, int page) {

        String countQ = "Select count (f.id) from Device f";
        Query countQuery = currentSession().createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();

        int lastPageNumber = (int) (Math.ceil((float) countResults / limit));

        Query selectQuery = query("FROM Device ");
        selectQuery.setFirstResult((page - 1) * limit);
        selectQuery.setMaxResults(limit);
        List<Device> usersPage = list(selectQuery);

        return new Paged<List<Device>>(page, limit, countResults, usersPage);
    }

    @Override
    public Long save(Device dev) throws HibernateException {
        super.persist(dev);
        return dev.getId();
    }

    @Override
    public Long update(Device dev, String[] params) {

        // Find person in DB and copy salt, secrete so the values will not be affected
        Optional<Device> personOptional = findById(dev.getId());
        /*personOptional.ifPresent(person1 -> {
            person.setSalt(person1.getSalt());
            person.setSecrete(person1.getSecrete());
            currentSession().detach(person1);
        });
        persist(person);*/
        return dev.getId();
    }

    @Override
    public void delete(Device dev) {
        currentSession().delete(dev);
    }
}
