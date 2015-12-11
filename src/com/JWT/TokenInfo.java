package com.JWT;

/**
 * created by IntelliJ IDEA
 *
 * @author zjc
 * @time 2015/12/4-11:31
 */
public class TokenInfo {
    private Long userId;
    private Long issued;
    private Long expires;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getIssued() {
        return issued;
    }

    public void setIssued(Long issued) {
        this.issued = issued;
    }

    public Long getExpires() {
        return expires;
    }

    public void setExpires(Long expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "userId=" + userId +
                ", issued=" + issued +
                ", expires=" + expires +
                '}';
    }
}
