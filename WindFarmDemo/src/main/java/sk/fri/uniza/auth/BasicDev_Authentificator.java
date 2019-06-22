package sk.fri.uniza.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import sk.fri.uniza.api.Device;
import sk.fri.uniza.db.DevicesDao;

import java.util.Optional;

public class BasicDev_Authentificator implements Authenticator<BasicCredentials,Device> {
    private DevicesDao dev_dao;

    public BasicDev_Authentificator(DevicesDao dev_dao)   {
        this.dev_dao = dev_dao;
    }
    
    @Override
    public Optional<Device> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Integer id = -1;
        Device dev = new Device();
        for (Integer i = 1;i<=dev_dao.getArrayL_Devices().size();i++) {
            dev = dev_dao.getDeviceFromLocalDB_WithID(i);
            if(dev.getName().equals(credentials.getUsername())) {
                id = i;
                break;
            }
        }

        if (id != -1 && dev.getSecret().equals(credentials.getPassword()))
        {
            System.out.println("bol som tu:_"+id);
            return Optional.of(new Device(credentials.getUsername(), dev.getName()));
        }
        return Optional.empty();
    }
}
