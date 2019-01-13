package connection;

import java.util.List;

public class ConnectManager {
    private List<String> ipList;

    public ConnectManager(){
        init();
    }

    private void init(){
        ipList = IpUtil.getLocalIPList();
    }

    public List<String> getIpList() {
        return ipList;
    }
}
