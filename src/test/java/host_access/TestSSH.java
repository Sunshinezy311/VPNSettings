package host_access;

public class TestSSH {
    public static void main(String[] args) {
        BySSH testSSH=new BySSH("149.28.88.53",
                               "root",
                               "qA3}3zd6m1AmB6va",
                               22);

        testSSH.accessDestHost("ifconfig");
    }
}
