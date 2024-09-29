package top.guoziyang.mydb.client;

import top.guoziyang.mydb.transport.Package;
import top.guoziyang.mydb.transport.Packager;

import java.util.LinkedList;

public class RoundTripper {
    private Packager packager;

    public RoundTripper(Packager packager) {
        this.packager = packager;
    }

    public Package roundTrip(Package pkg) throws Exception {
        packager.send(pkg);
        return packager.receive();
    }

    public void close() throws Exception {
        packager.close();
    }
}

class Solution {
    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<>();
        LinkedList<Integer> stk2 = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for(int i = 0 ; i < s.length(); i ++){
            char c  = s.charAt(i);
            if(Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if(c == '['){
                stk2.push(num);
                num = 0;
                StringBuilder st = new StringBuilder();
                for(int j = i + 1; j < s.length(); j++){
                    c = s.charAt(j);
                    if(c >= 'a' && c <= 'z'){
                        st.append(c);
                        i++;
                    }
                    else{
                        stk.push(st.toString());
                        break;
                    }
                }
            }
            else if (c == ']'){
                int tmp = stk2.pop();
                StringBuilder sp = new StringBuilder();
                String tp = stk.pop();
                while(tmp>0){
                    sp.append(tp);
                    tmp--;
                }
                if(stk2.isEmpty()){
                    sb.append(sp.toString());
                }else{
                    stk.push(sp.toString());
                }
            }
            else {
                StringBuilder sp = new StringBuilder();
                sp.append(c);
                for(int j = i + 1; j < s.length(); j++){
                    c = s.charAt(j);
                    if(c >= 'a' && c <= 'z'){
                        sp.append(c);
                        i++;
                    }
                    else{
                        sb.append(sp.toString());
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }
}