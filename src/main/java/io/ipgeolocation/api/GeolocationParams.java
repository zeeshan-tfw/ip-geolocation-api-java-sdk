package io.ipgeolocation.api;

public class GeolocationParams {
    private String ip;
    private String fields;
    private String[] ips;

    public GeolocationParams() {
        this.ip = "";
        this.fields = "";
    }

    public void setIp(String ip) {
        this.ip = Strings.nullToEmpty(ip);
    }

    public String getIp() {
        return this.ip;
    }

    public void setFields(String fields) {
        this.fields = Strings.nullToEmpty(fields);
    }

    public String getFields() {
        return this.fields;
    }

    public void setIps(String[] ips) {
        this.ips = ips;
    }

    public String[] getIps() {
        return this.ips;
    }
}
