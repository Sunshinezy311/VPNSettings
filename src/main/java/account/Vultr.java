package account;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

//创建了一个user实体，含有属性和操作。 注意类名前用@XmlRootElement 注解，可以将实体映射为xml
@XmlRootElement
public class Vultr implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ip;
    private String user;
    private String password;
    private int port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
