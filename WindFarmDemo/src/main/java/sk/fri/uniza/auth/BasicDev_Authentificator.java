package sk.fri.uniza.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import sk.fri.uniza.api.Device;
import sk.fri.uniza.db.DevicesDao;

import java.util.Optional;

public class BasicDev_Authentificator implements Authenticator<BasicCredentials,Device> {
    private DevicesDao dev_dao;
    private Device dev;// = new Device();

    public BasicDev_Authentificator(DevicesDao dev_dao)   {
        this.dev_dao = dev_dao;
    }

    public void setDao(DevicesDao dev_dao)  {
        this.dev_dao = dev_dao;
    }

    @Override
    public Optional<Device> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Integer id = -1;
        dev= new Device();
        for (Integer i = 1;i<=dev_dao.getArrayL_Devices().size();i++) {
            dev = dev_dao.getDeviceFromLocalDB_WithID(i);
            if(dev.getName().equals(credentials.getUsername())) {
                id = i;
                break;
            }
        }
        if (id != -1) {
            if (dev.getSecret().equals(credentials.getPassword()))
            {
                return Optional.of(new Device(credentials.getUsername(), dev.getRole()));
            }
        }
        return Optional.empty();
        /*try {
            dev = dev_dao.findDeviceWithName_DB(credentials.getUsername());
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
            if (dev != null) {
            if (dev.getSecret().equals(credentials.getPassword()))
            {
                return Optional.of(new Device(credentials.getUsername(), dev.getName()));
            }
        }
        return Optional.empty();*/
    }


    @UnitOfWork
    public void generateDevices() {
        for(Integer i = 1; i<=dev_dao.getArrayL_Devices().size();i++)    {
            dev_dao.save(dev_dao.getDeviceFromLocalDB_WithID(i));
        }
    }

}
/*        dev = dev_dao.findDeviceWithName_DB(credentials.getUsername());
        if (dev != null) {
            if (dev.getSecret().equals(Device.s_generateHashSecrete(dev.getSalt(),credentials.getPassword())))
            {
                return Optional.of(new Device(credentials.getUsername(), dev.getName()));
            }
        }
        return Optional.empty();
        */