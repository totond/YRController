package connection;

import java.util.List;

public class ConnetManager {
    private List<String> ipList;

    public ConnetManager(){
        init();
    }

    private void init(){
        ipList = IpUtil.getLocalIPList();
    }

    public List<String> getIpList() {
        return ipList;
    }
}
