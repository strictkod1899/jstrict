package ru.strict.components;

import ru.strict.validate.ValidateBaseValue;

import java.util.Objects;

public class Url implements Cloneable {

    private String protocol;
    private String host;
    private String address;
    private String url;

    public Url(String address) {
        this.address = address;
        createUrl();
    }

    public Url(String host, String address) {
        this.host = host;
        this.address = address;
        createUrl();
    }

    public Url(String protocol, String host, String address) {
        this.protocol = protocol;
        this.host = host;
        this.address = address;
        createUrl();
    }

    private void createUrl(){
        if(!ValidateBaseValue.isEmptyOrNull(protocol)
                && !ValidateBaseValue.isEmptyOrNull(host)
                && !ValidateBaseValue.isEmptyOrNull(address)) {
            url = String.format("%s://%s/%s", protocol, host, address);
        } else if(ValidateBaseValue.isEmptyOrNull(protocol)
                && !ValidateBaseValue.isEmptyOrNull(host)
                && !ValidateBaseValue.isEmptyOrNull(address)) {
            url = String.format("%s/%s", host, address);
        } else if(ValidateBaseValue.isEmptyOrNull(protocol)
                && ValidateBaseValue.isEmptyOrNull(host)
                && !ValidateBaseValue.isEmptyOrNull(address)) {
            url = String.format("/%s", address);
        }
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString(){
        return getUrl();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url object = (Url) o;
        return Objects.equals(protocol, object.protocol) &&
                Objects.equals(host, object.host) &&
                Objects.equals(address, object.address) &&
                Objects.equals(url, object.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(protocol, host, address, url);
    }

    @Override
    public Url clone() {
        try {
            return (Url)super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
