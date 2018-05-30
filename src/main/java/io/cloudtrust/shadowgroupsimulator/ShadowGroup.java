package io.cloudtrust.shadowgroupsimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Definition of a ShadowGroup value. This is used to create a json representation of the shadowgroup answer
 */
public class ShadowGroup {

    private final int id = 1;
    private final List<String> values;

    public ShadowGroup(String username, String applicationUrl, String mobilityStatus) {
        values = new ArrayList<>(Arrays.asList("alpha", "bravo", "charlie", "delta", username));
        if (applicationUrl != null && !applicationUrl.isEmpty()) {
            values.add(applicationUrl);
        }
        if (mobilityStatus != null && !mobilityStatus.isEmpty()) {
            values.add(mobilityStatus);
        }
    }

    public int getId() {
        return id;
    }

    public List<String> getValues() {
        return values;
    }

}
