package sk.fri.uniza.api;

import org.apache.commons.lang.ArrayUtils;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Date;
import java.util.Random;

@Entity
@Table (name="Device_Table")

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
/*@org.hibernate.annotations.NamedQueries(
        {
                @org.hibernate.annotations.NamedQuery(
                        name = "sk.fri.uniza.api.getAlldev",
                        query = "SELECT p FROM Devices p"
                )
        })*/
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

    public Device(String nname, String location, String password,String baseUrl)    {
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

    @Column(name="D_BaseUrl")
    public String getBaseUrl() {
        return baseUrl;
    }

    /*  public boolean isCorrectPass(String password)   {
        byte[] new_secret = s_generateHashSecrete(salt,password);
        return new_secret.equals(secret);
    }*/

    /*public void genSalt()     {
        salt = new byte[8];
        rand.nextBytes(salt);
    }

    public byte[] getSalt()     {
        return salt;
    }
*/
    @Column(name="D_Roles")
    public String getRole()    {
        return role;
    }

    @Column(name="D_secrete")
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId()  {
        return id;
    }

    public void setId(Long id)  {
        this.id = id;
    }

    @Column(name="D_name")
    public String getName() {
        return nname;
    }

    public void setName(String name) {
        this.nname = name;
    }

    @Column(name="D_location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
