
package parking.utils;

import parking.entity.NhanVien;

public class Authenticated {
    public static NhanVien user = null;
    public static String quenMK = "";
    
    public static boolean isLogin(){
        return Authenticated.user!=null;
    }
    
    public static void clear(){
        Authenticated.user = null;
    }
    
    public static boolean isManage(){
        return Authenticated.user.isVaiTro()&& Authenticated.isLogin();
    }
    
}
