package model;

import java.util.ArrayList;

public class Stage {
    private final Integer stageId;
    private final String stageName;

    public static ArrayList<Stage> stageArray = new ArrayList<>();

    public Stage(Integer stageId, String stageName) {
        this.stageId = stageId;
        this.stageName = stageName;
    }

    public Integer getStageId() {
        return stageId;
    }

    public String getStageName() {
        return stageName;
    }
}
