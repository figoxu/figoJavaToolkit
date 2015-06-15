package me.figoxu.common.domain;

/**
 * .
 * User: figo
 * Date: 12-8-9
 * Time: 下午10:12
 *
 */
public class User extends BaseDomain{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
