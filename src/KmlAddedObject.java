public class KmlAddedObject {
    private Long areaId;
    private String kmlAdded;

    public KmlAddedObject(Long areaId, String kmlAdded) {
        this.areaId = areaId;
        this.kmlAdded = kmlAdded;
    }

    public Long getAreaId() {
        return areaId;
    }

    public String getKmlAdded() {
        return kmlAdded;
    }
}
