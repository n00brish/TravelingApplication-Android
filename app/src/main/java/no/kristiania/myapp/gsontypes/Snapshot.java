
package no.kristiania.myapp.gsontypes;

import java.util.HashMap;
import java.util.Map;

public class Snapshot {

    private Long time;
    private String reason;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Snapshot() {
    }

    /**
     * 
     * @param reason
     * @param time
     */
    public Snapshot(Long time, String reason) {
        super();
        this.time = time;
        this.reason = reason;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
