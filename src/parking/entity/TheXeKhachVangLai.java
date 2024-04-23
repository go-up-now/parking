
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheXeKhachVangLai {
    private String maThe;
    private String maLoaiXe;

    @Override
    public String toString() {
        return maThe;
    }
}
