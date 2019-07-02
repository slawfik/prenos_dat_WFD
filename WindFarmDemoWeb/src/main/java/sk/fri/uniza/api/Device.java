package sk.fri.uniza.api;

import org.apache.commons.lang.ArrayUtils;

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
    private String baseUrl;

    private Long id;
    //private byte[] salt;

    public Device()    {}

    public Device(String nname, String location, String password, String baseUrl)    {
        this.nname = nname;
        this.location = location;
        this.baseUrl = baseUrl;
        /*if (password != null)   {
            genSalt();
            this.secret = s_generateHashSecrete(getSalt(),password);
        }*/
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

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getRole()    {
        return role;
    }

    public String getSecret() {
        return secret;
    }

/*    @Column(name="D_salt")
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }*/

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
