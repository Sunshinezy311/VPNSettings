package HostAccess;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class BySSH {
    private String ipAddress;
    private String username;
    private String password;
    private int sshPort;
    //String hostsCmd;

    public BySSH(String ip, String user, String pass, int port ){
        ipAddress=ip;
        username=user;
        password=pass;
        sshPort=port;
    }

    //cmd表示指令
    public void accessDestHost(String cmd){
        JSch jsch=new JSch();
        try {
            //ssh会话对象 可以设置多种配置 常用setPassword etConfig setTimeout connect disconnect
            //初始化参数中包括 用户名 ip和 端口号
            Session sshSession=jsch.getSession(username,ipAddress,sshPort);
            sshSession.setPassword(password);
            //？？？这里不懂
            Properties config=new Properties();
            config.put("StrictHostKeyChecking","no");
            sshSession.setConfig(config);//为Session对象设置Properties
            sshSession.setTimeout(30000);
            sshSession.connect();

            //继承关系： ChannelExec->ChannelSession->Channel
            //这个功能是想目标主机发送指令流
            ChannelExec channelExec=(ChannelExec)sshSession.openChannel("exec");
            channelExec.setCommand(cmd);//转换化为bit
            channelExec.setInputStream(null);//属于Channel父类的方法, 此处声明相当于初始化一个inputstream，用于接受cmd返回的结果
            channelExec.setErrStream(System.err);
            channelExec.connect();//属于Channel父类的方法

            InputStream inputStream=channelExec.getInputStream();//把连接那端的结果返回到该inputstream中
            BufferedReader bufferedReader=new BufferedReader(
                                          new InputStreamReader(
                                                  inputStream, Charset.forName("UTF-8")));//把输入流的进行解码

            String outBuffer=null;
            //StringBuffer stringBufferReadline=new StringBuffer();//逐行
            while((outBuffer=bufferedReader.readLine())!=null){
                //stringBufferReadline.append(buffer);
                System.out.println(outBuffer);
            }
            bufferedReader.close();
            channelExec.disconnect();
            if(null!=sshSession){
                sshSession.disconnect();
            }
        }catch(Exception e){//写的过于简单了
            e.printStackTrace();
        }finally {

        }


    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSshPort() {
        return sshPort;
    }

    public void setSshPort(int sshPort) {
        this.sshPort = sshPort;
    }
}
