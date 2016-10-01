package entities;

public class UserCorrelation {
    String username;
    Integer correlation;

    public UserCorrelation(String username, Integer correlation) {
        this.username = username;
        this.correlation = correlation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCorrelation() {
        return correlation;
    }

    public void setCorrelation(Integer correlation) {
        this.correlation = correlation;
    }
}
