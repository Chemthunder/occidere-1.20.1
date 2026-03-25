package net.chemthunder.occidere.impl.index.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface OccidereUUIDs {
    List<UUID> UUIDS = new ArrayList<>();

    UUID HSTAR = UUID.fromString("edcc4442-f423-4b3b-bb58-c6661085195c");

    static void init() {
        UUIDS.add(HSTAR);
    }
}