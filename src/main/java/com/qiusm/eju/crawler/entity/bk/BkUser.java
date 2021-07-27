package com.qiusm.eju.crawler.entity.bk;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.qiusm.eju.crawler.entity.base.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qiushengming
 */
@Data
@TableName("bk_user")
@EqualsAndHashCode(callSuper = true)
public class BkUser
        extends SuperEntity<BkUser> {

    @TableField("phone_no")
    private String phoneNo;

    private String password;

    @TableField("device_id")
    private String deviceId;

    private String token;

    private Integer state;

    @TableField("state_desc")
    private String stateDesc;

    public BkUser() {

    }

    private BkUser(BkUser.Builder builder) {
        this.phoneNo = builder.phoneNo;
        this.password = builder.password;
        this.deviceId = builder.deviceId;
    }

    public void setState(Integer state) {
        this.state = state;
        switch (this.state) {
            case 1:
                this.stateDesc = "有效";
                break;
            case 91:
                this.stateDesc = "登录失败";
                break;
            case 99:
                this.stateDesc = "需要重新登录";
                break;
            default:
                break;
        }
    }

    public static BkUser.Builder builder() {
        return new BkUser.Builder();
    }

    public static final class Builder {
        String phoneNo;
        String deviceId;
        String password;

        public BkUser.Builder phoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public BkUser.Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public BkUser.Builder password(String password) {
            this.password = password;
            return this;
        }

        public BkUser build() {
            return new BkUser(this);
        }
    }

}