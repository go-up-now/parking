
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiXe {
    private String maLoaiXe;
    private String tenLoaiXe;
    private String maKhu;
    private String MaLoaiGia;

    @Override
    public String toString() {
        return maLoaiXe + " (" + tenLoaiXe + ")";
    }
}
