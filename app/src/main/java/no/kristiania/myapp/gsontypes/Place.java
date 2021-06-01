
package no.kristiania.myapp.gsontypes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Place {

    private Long id;
    private String type;
    private String name;
    private String countryCode;
    private String comments;

    private Double dieselPrice;
    private String mapboxIcon;
    private String banner;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *  args constructor in arrays
     * 
     */
    public Place() {
    }


      //i only care about this information

     // images  , type, , id , name

    public Place(Long id, String type, String name, String externalLink1, List<Image> images, List<Object> reviews) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
