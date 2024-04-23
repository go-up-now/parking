
package parking.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhuGuiXe {
    private String maKhu;
    private String tenKhu;
    private String diaChi;
    private int soLuongChoDau;

    @Override
    public String toString() {
        return tenKhu;
    }

}
