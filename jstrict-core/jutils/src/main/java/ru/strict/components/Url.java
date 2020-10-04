package ru.strict.components;

import ru.strict.validate.CommonValidate;

import java.util.Objects;

public class Url implements Cloneable {
    private final String protocol;
    private final String host;
    private final String address;
    private final String url;

    public Url(String address) {
        this(null, null, address);
    }

    public Url(String host, String address) {
        this(null, host, address);
    }

    public Url(String protocol, String host, String address) {
        this.protocol = protocol;
        this.host = host;
        this.address = address;
        this.url = createUrl();
    }

    private String createUrl() {
        if (!CommonValidate.isEmptyOrNull(protocol)
                && !CommonValidate.isEmptyOrNull(host)
                && !CommonValidate.isEmptyOrNull(address)) {
            return String.format("%s://%s/%s", protocol, host, address);
        } else if (CommonValidate.isEmptyOrNull(protocol)
                && !CommonValidate.isEmptyOrNull(host)
                && !CommonValidate.isEmptyOrNull(address)) {
            return String.format("%s/%s", host, address);
        } else if (CommonValidate.isEmptyOrNull(protocol)
                && CommonValidate.isEmptyOrNull(host)
                && !CommonValidate.isEmptyOrNull(address)) {
            return String.format("/%s", address);
        }

        throw new IllegalArgumentException("Fail url creating");
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
    public String toString() {
        return getUrl();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
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
            return (Url) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
