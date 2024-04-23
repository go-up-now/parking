
package parking.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheXeCuDan {
    private String maThe;
    private Date thoiGianDangKy = new Date();
    private String maCuDan;
    private String maLoaiXe;

    @Override
    public String toString() {
        return maThe;
    }
}
