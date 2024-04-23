
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuDan {
    private String maCuDan;
    private String ho;
    private String ten;
    private int gioiTinh;
    private String soDienThoai;
    private String email;
}
