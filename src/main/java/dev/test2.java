package dev;

import java.util.*;

public class test2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
    }

    static class Solution {
        public String[] solution(int n, String[] queries) {
            String[] answer = {};
            DesktopRepository desktopRepository = new DesktopRepository();
            IpRepository ipRepository = new IpRepository();

            //ip 저장
            for(int i = 1; i <= n; i++) {
                Ip ip = new Ip("192.168.0."+i, true, false);
                ipRepository.save(ip);
            }

            //요청
            Long idx = 0L;
            for(String s : queries) {
                String[] str = s.split(" ");
                //ip요청
                if(str[1].equals("request")) {
                    //ip 할당 받은적 없는 경우
                    if(desktopRepository.find(idx).pastIp == null) {
                        boolean applyHistory = ipRepository.isApplyHistory(n);
                        if (applyHistory) {
                            Ip ip = ipRepository.findMinIpByUsableAndApplyHistory(n);
                            Desktop desktop = new Desktop(str[0], ip.ipAddr, ip.ipAddr);
                            desktopRepository.save(desktop);
                        } else if (!applyHistory) {
                            Ip ip = ipRepository.findMinIpByUsable(n);
                            Desktop desktop = new Desktop(str[0], ip.ipAddr, ip.ipAddr);
                            desktopRepository.save(desktop);
                        } else {
                            //요청 거부
                            Desktop desktop = new Desktop(str[0], "reject", "reject");
                            desktopRepository.save(desktop);
                        }
                    }
                    //ip 할당 받은적 있는 경우
                    else {
                        Ip ip = ipRepository.findByIpAddr(desktopRepository.find(idx).pastIp);
                        if (ip != null) {
                            Desktop desktop = new Desktop(str[0], ip.ipAddr, ip.ipAddr);
                            desktopRepository.save(desktop);
                        } else {
                            boolean applyHistory = ipRepository.isApplyHistory(n);
                            if(applyHistory) {
                                Ip ip2 = ipRepository.findMinIpByUsableAndApplyHistory(n);
                                Desktop desktop = new Desktop(str[0], ip2.ipAddr, ip2.ipAddr);
                                desktopRepository.save(desktop);
                            } else if(!applyHistory){
                                Ip ip2 = ipRepository.findMinIpByUsable(n);
                                Desktop desktop = new Desktop(str[0], ip2.ipAddr, ip2.ipAddr);
                                desktopRepository.save(desktop);
                            } else {
                                //요청 거부
                                Desktop desktop = new Desktop(str[0], "reject", "reject");
                                desktopRepository.save(desktop);
                            }
                        }
                    }
                }
                //ip반납
                else if(str[1].equals("release")){
                    //desktopRepository.
                }
            }


            return answer;
        }
    }

    static class Desktop {
        private String name;
        private String currentIp; //현재 할당된 아이피
        private String pastIp; //과거 할당된 아이피

        public Desktop(String name, String currentIp, String pastIp) {
            this.name = name;
            this.currentIp = currentIp;
            this.pastIp = pastIp;
        }
    }

    static class Ip {
        private String ipAddr;
        private boolean usable; //할당 가능 여부
        private boolean applyHistory; //할당 이력

        public Ip(String ipAddr, boolean usable, boolean applyHistory) {
            this.ipAddr = ipAddr;
            this.usable = usable;
            this.applyHistory = applyHistory;
        }
    }

    static class DesktopRepository {
        private static Long id = 0L;
        private Map<Long, Desktop> map = new HashMap<>();

        public void save(Desktop desktop) {
            map.put(++id, desktop);
        }

        public Desktop find(Long id) {
            return map.get(id);
        }
    }

    static class IpRepository {
        private static Long id = 0L;
        private Map<Long, Ip> map = new HashMap<>();

        public void save(Ip ip) {
            map.put(++id, ip);
        }

        public Ip find(Long id) {
            return map.get(id);
        }

        public Ip findByIpAddr(String ipAddr) {
            Long index = 1L;
            for(int i = 0; i < map.size(); i++) {
                Ip ip = map.get(index++);
                if(ip.usable) {
                    if(ip.ipAddr.equals(ipAddr)) return ip;
                }
            }
            return null;
        }

        //ip 할당 유무
        public boolean isApplyHistory(int n) {
            Long index = 1L;
            for(int i = 0; i < n; i++) {
                Ip ip = map.get(index++);
                if(ip.applyHistory) {
                    return true;
                }
            }
            return false;
        }

        //현재 사용하지 않는 ip 중 제일 작은 값
        public Ip findMinIpByUsableAndApplyHistory(int n) {
            Long index = 1L;
            for(int i = 0; i < n; i++) {
                Ip ip = map.get(index++);
                if(ip.usable && !ip.applyHistory) {
                    return ip;
                }
            }
            return null;
        }

        public Ip findMinIpByUsable(int n) {
            Long index = 1L;
            for(int i = 0; i < n; i++) {
                Ip ip = map.get(index++);
                if(ip.usable) {
                    return ip;
                }
            }
            return null;
        }
    }
}
