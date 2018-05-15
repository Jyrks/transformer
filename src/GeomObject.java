public class GeomObject {
    private Long areaId;
    private String geom;
    private Double areaInMeters;
    private String pointOnSurface;

    public GeomObject(Long areaId, String geom, Double areaInMeters, String pointOnSurface) {
        this.areaId = areaId;
        this.geom = geom;
        this.areaInMeters = areaInMeters;
        this.pointOnSurface = pointOnSurface;
    }

    public Long getAreaId() {
        return areaId;
    }

    public String getGeom() {
        return geom;
    }

    public Double getAreaInMeters() {
        return areaInMeters;
    }

    public String getPointOnSurface() {
        return pointOnSurface;
    }
}
