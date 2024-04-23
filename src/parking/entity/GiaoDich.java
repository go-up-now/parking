
package parking.entity;

import parking.utils.DateHelper;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaoDich {
    private int maGiaoDich;
    private Date thoiGianVao = new Date();
    private Date thoiGianRa = null;
    private long soNgayGui;
    private String bienSoXe = null;
    private double phiGuiXe = 0;
    private boolean trangThai;
    private String hinh;
    private int maNhanVien;
    private String theCuDanID;
    private String theKhachVangLaiID;
    
    public int getSoNgayGuiXe(){
        if(this.thoiGianRa != null){
//            long soNgayGuiXe = this.thoiGianRa.getTime()  - this.thoiGianVao.getTime();
//            System.out.println(soNgayGuiXe);
//            System.out.println((int) (soNgayGuiXe/(1000*60*60*24)) + 1);
//            return (int) (soNgayGuiXe/(1000*60*60*24)) + 1;

               String ngayVao = DateHelper.DatetoString(thoiGianVao, "yyyy-MM-dd");
               Date ngayVao1;
            try {
                ngayVao1 = DateHelper.StringtoDate(ngayVao, "yyyy-MM-dd");
               String ngayRa = DateHelper.DatetoString(thoiGianRa, "yyyy-MM-dd");
               Date ngayRa1;
                ngayRa1 = DateHelper.StringtoDate(ngayRa, "yyyy-MM-dd");
                long soNgay = ngayRa1.getTime() - ngayVao1.getTime();
                
                return (int) (soNgay/(1000*60*60*24)) + 1;
            } catch (ParseException ex) {
                Logger.getLogger(GiaoDich.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return 0;
    }

    @Override
    public String toString() {
        return theCuDanID;
    }

}
