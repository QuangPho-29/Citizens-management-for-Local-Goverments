package com.example.introductiontose.model;

import java.time.LocalDateTime;

/**
 * Lớp CCCD đại diện cho Chứng minh nhân dân (CCCD) của một người.
 */
public class CCCD {
    private String soCccd;
    private LocalDateTime ngayCap;
    private String noiCap;
    
    /**
     * Khởi tạo một đối tượng CCCD với các thông tin cơ bản.
     *
     * @param soCccd Số CCCD của người sở hữu.
     * @param ngayCap Ngày cấp CCCD.
     * @param noiCap Nơi cấp CCCD.
     */
    public CCCD(String soCccd, LocalDateTime ngayCap, String noiCap) {
        this.soCccd = soCccd;
        this.ngayCap = ngayCap;
        this.noiCap = noiCap;
    }
    
    /**
     * Trả về số CCCD của người sở hữu.
     *
     * @return Số CCCD.
     */
    public String getSoCccd() {
        return soCccd;
    }
    
    /**
     * Thiết lập số CCCD của người sở hữu.
     *
     * @param soCccd Số CCCD mới.
     */
    public void setSoCccd(String soCccd) {
        this.soCccd = soCccd;
    }
    
    /**
     * Trả về ngày cấp CCCD.
     *
     * @return Ngày cấp CCCD.
     */
    public LocalDateTime getNgayCap() {
        return ngayCap;
    }
    
    /**
     * Thiết lập ngày cấp CCCD.
     *
     * @param ngayCap Ngày cấp CCCD mới.
     */
    public void setNgayCap(LocalDateTime ngayCap) {
        this.ngayCap = ngayCap;
    }
    
    /**
     * Trả về nơi cấp CCCD.
     *
     * @return Nơi cấp CCCD.
     */
    public String getNoiCap() {
        return noiCap;
    }
    
    /**
     * Thiết lập nơi cấp CCCD.
     *
     * @param noiCap Nơi cấp CCCD mới.
     */
    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }
}
