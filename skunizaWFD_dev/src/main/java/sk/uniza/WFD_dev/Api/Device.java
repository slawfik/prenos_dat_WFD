package sk.uniza.WFD_dev.Api;

import org.apache.commons.lang3.ArrayUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Date;
import java.util.Random;

public class Device implements Principal {
    private static Random rand = new Random((new Date()).getTime());
    private String nname;
    private String location;
    private String secret;
    private String role;
    private Long id;
    private byte[] salt;

    public Device()    {}

    public Device(String nname, String location, String password)    {
        this.nname = nname;
        this.location = location;
        secret = password;
        role = "default";
    }

    public Device(String name, String roles) {
        this.nname = name;
        this.role = roles;
    }

    public static byte[] s_generateHashSecrete(byte[] salt ,String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return md.digest(ArrayUtils.addAll(salt, password.getBytes()));
    }

  /*  public boolean isCorrectPass(String password)   {
        byte[] new_secret = s_generateHashSecrete(salt,password);
        return new_secret.equals(secret);
    }*/

    public void genSalt()     {
        salt = new byte[8];
        rand.nextBytes(salt);
    }

    public byte[] getSalt()     {
        return salt;
    }

    public String getRole()    {
        return role;
    }

    public String getSecret() {
        return secret;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getId()  {
        return id;
    }

    public void setId(Long id)  {
        this.id = id;
    }

    public String getName() {
        return nname;
    }

    public void setName(String name) {
        this.nname = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
