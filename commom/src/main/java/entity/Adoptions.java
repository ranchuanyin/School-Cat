package entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Adoptions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 领养记录id
     */
    @TableId(value = "ado_id", type = IdType.AUTO)
    private Integer adoId;

    /**
     * 领养人id
     */
    private Integer userId;

    /**
     * 宠物id
     */
    private Integer animalId;

    /**
     * 领养状态：0失败，1成功，2处理中
     */
    private Integer adoStatus;
}
