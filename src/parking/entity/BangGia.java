
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BangGia {
    private String maLoaiGia;
    private String thoiGian;
    private double gia = 0;

    @Override
    public String toString() {
        return thoiGian + " (" + gia + ")";
    }
    
    
}
