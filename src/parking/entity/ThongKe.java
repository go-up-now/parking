
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongKe {
    private int nam;
    private int thang;
    private int ngay;
    private String maKhu;


    @Override
    public String toString() {
        return "Năm " + nam;
    }
    
    public String toStringThang() {
        return "" + thang;
    }
}
