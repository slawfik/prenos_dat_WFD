package sk.fri.uniza.auth;

import io.dropwizard.auth.Authorizer;
import sk.fri.uniza.api.Device;

public class BasicDev_Authorizer implements Authorizer<Device> {
    @Override
    public boolean authorize(Device devices, String role) {
        return devices.getRole() != null && devices.getRole().equals(role);
    }
}
