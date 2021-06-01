
package no.kristiania.myapp.gsontypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationDetail {

    private List<Snapshot> snapshots = null;
    private Place place;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public LocationDetail() {
    }


    public LocationDetail(List<Snapshot> snapshots, Place place) {
        super();
        this.snapshots = snapshots;
        this.place = place;
    }

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
